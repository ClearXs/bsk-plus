package com.bsk.security.shiro.filter;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.bsk.cache.redis.constant.RedisKey;
import com.bsk.cache.util.RedisUtils;
import com.bsk.common.constant.StringPool;
import com.bsk.common.modle.ApiResult;
import com.bsk.common.util.AppUtils;
import com.bsk.common.util.HttpUtils;
import com.bsk.security.properties.TokenProperties;
import com.bsk.security.shiro.constant.TokenConstant;
import com.bsk.security.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BskLogoutFilter extends LogoutFilter {

    private static Logger logger = LoggerFactory.getLogger(BskLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        TokenUtils tokenUtils = AppUtils.getBean(TokenUtils.class);
        RedisUtils redisUtils = AppUtils.getBean(RedisUtils.class);
        TokenProperties tokenProperties = AppUtils.getBean(TokenProperties.class);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken = httpServletRequest.getHeader(TokenConstant.ACCESS_TOKEN);
        Subject subject = getSubject(request, response);
        subject.logout();
        // 设置toke在黑名单
        if (StringUtils.isEmpty(accessToken)) {
            logger.error("-----无法获取token ");
            ApiResult<String> apiResult = new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "无法获取token");
            HttpUtils.resReturn(WebUtils.toHttp(response), HttpStatus.INTERNAL_SERVER_ERROR.value(), apiResult);
            return false;
        }
        try {
            String account = tokenUtils.getClaim(accessToken, TokenConstant.ACCOUNT, String.class);
            DateTime issue = DateUtil.date();
            DateTime expire = DateUtil.date(issue.getTime() + tokenProperties.getExpire());
            // token 放在黑名单并设置为与token相同时间的过期时间
            redisUtils.setExpire(RedisKey.TOKEN_BLACK_LIST + StringPool.UNDERSCORE + account, accessToken, expire.getTime());
        } catch (Exception e) {
            logger.error("-----token过期: " + accessToken);
            HttpUtils.resOk(WebUtils.toHttp(response), "无法获取token");
            return false;
        }
        HttpUtils.resOk(WebUtils.toHttp(response), "注销成功！");
        // 不走后面filter，
        return false;
    }
}
