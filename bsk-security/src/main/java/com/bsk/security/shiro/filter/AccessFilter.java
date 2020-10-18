package com.bsk.security.shiro.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bsk.cache.redis.constant.RedisKey;
import com.bsk.cache.util.RedisUtils;
import com.bsk.common.constant.StringPool;
import com.bsk.common.util.AppUtils;
import com.bsk.common.util.BeanUtils;
import com.bsk.common.util.HttpUtils;
import com.bsk.security.shiro.BskToken;
import com.bsk.security.shiro.constant.TokenConstant;
import com.bsk.security.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问控制的filter，除开登录外的接口都需要token认证
 * @author jiangw
 * @date 2020/9/16 23:31
 * @since 1.0
 */
public class AccessFilter extends BasicHttpAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录尝试，判断header是否含有token，若没有token则返回false，表示当前请求被拒绝，返回401错误{@link BasicHttpAuthenticationFilter#onAccessDenied(ServletRequest, ServletResponse)}
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String accessToken = req.getHeader(TokenConstant.ACCESS_TOKEN);
        return StringUtils.isNotEmpty(accessToken);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String accessToken = getAuthzHeader(request);
        // 解析token
        TokenUtils tokenUtils = AppUtils.getBean(TokenUtils.class);
        // 判断当前token是否过期
        boolean isExpire = tokenUtils.isExpire(accessToken);
        // 若没用过期，判断该token是否存在于黑名单中
        String account = tokenUtils.getClaim(accessToken, TokenConstant.ACCOUNT, String.class);
        if (isExpire) {
            Object o = redisUtils.get(RedisKey.TOKEN_BLACK_LIST + StringPool.UNDERSCORE + account);
            if (BeanUtils.isEmpty((o))) {
                throw new RuntimeException("token失效");
            }
        }
        String password = tokenUtils.getClaim(accessToken, TokenConstant.PASSWORD, String.class);
        Boolean rememberMe = tokenUtils.getClaim(accessToken, TokenConstant.REMEMBER_ME, Boolean.class);
        return new BskToken(account, password, rememberMe);
    }

    @Override
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(TokenConstant.ACCESS_TOKEN);
    }

    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        String token = getAuthzHeader(request);
        // 解析token
        TokenUtils tokenUtils = AppUtils.getBean(TokenUtils.class);
        RedisUtils redisUtils = AppUtils.getBean(RedisUtils.class);
        String account = tokenUtils.getClaim(token, TokenConstant.ACCOUNT, String.class);
        String password = tokenUtils.getClaim(token, TokenConstant.PASSWORD, String.class);
        Boolean rememberMe = tokenUtils.getClaim(token, TokenConstant.REMEMBER_ME, Boolean.class);
        String refreshTokenKey = RedisKey.PREFIX + StringPool.UNDERSCORE + RedisKey.REFRESH_ACCESS_TOKEN_KEY + StringPool.UNDERSCORE + account;
        if (redisUtils.isExits(refreshTokenKey)) {
            String refreshTime = (String) redisUtils.get(refreshTokenKey);
            String tokenTime = tokenUtils.getClaim(token, TokenConstant.ACCESS_TIMESTAMP, String.class);
            // 若token时间与缓存的时间一致，则直接刷新token
            if (refreshTime.equals(tokenTime)) {
                BskToken bskToken = new BskToken(account, password, rememberMe);
                getSubject(request, response).login(bskToken);
                // 设置响应的Header头新Token
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader(TokenConstant.ACCESS_TOKEN, token);
                return true;
            }
        }
        return false;
    }

    /**
     * 是否允许访问，
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            if (isLoginAttempt(request, response)) {
                this.executeLogin(request, response);
                return true;
            }
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            // createToken失败抛出异常
            if (throwable instanceof SignatureVerificationException) {
                HttpUtils.resError(WebUtils.toHttp(response), e.getMessage());
                // token过期自动刷新token
            } else if (throwable instanceof TokenExpiredException) {
                if (refreshToken(request, response)) {
                    return true;
                } else {
                    HttpUtils.resError(WebUtils.toHttp(response), "token过期");
                }
             } else {
                HttpUtils.resError(WebUtils.toHttp(response), e.getMessage());
            }
            return false;
        }
        return false;
    }

    /**
     * 当执行{@link #isAccessAllowed(ServletRequest, ServletResponse, Object)}返回false后执行，即处理访问拒绝后的逻辑，这里重写使之返回统一的信息
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpUtils.resError(WebUtils.toHttp(response), "请登录");
        return false;
    }
}
