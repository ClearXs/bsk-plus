package com.bsk.security.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bsk.cache.redis.constant.RedisKey;
import com.bsk.cache.util.RedisUtils;
import com.bsk.common.constant.StringPool;
import com.bsk.security.properties.TokenProperties;
import com.bsk.security.shiro.constant.TokenConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成一个token，负载用于存储用户的账号
     * @param account 账号
     * @return token
     */
    public String createToken(String account, String password, boolean rememberMe) {
        DateTime issue = DateUtil.date();
        DateTime expire = DateUtil.date(issue.getTime() + tokenProperties.getExpire());
        Map<String, Object> headers = new HashMap<>(2);
        headers.put("tye", tokenProperties.getType());
        headers.put("alg", tokenProperties.getAlgorithm());
        redisUtils.setExpire(RedisKey.PREFIX + StringPool.UNDERSCORE + RedisKey.REFRESH_ACCESS_TOKEN_KEY + StringPool.UNDERSCORE + account, issue.getTime());
        return JWT.create().
                withHeader(headers).
                withClaim(TokenConstant.ACCOUNT, account).
                withClaim(TokenConstant.PASSWORD, password).
                withClaim(TokenConstant.REMEMBER_ME, rememberMe).
                withClaim(TokenConstant.ACCESS_TIMESTAMP, issue.getTime()).
                withExpiresAt(expire).
                withIssuedAt(issue).
                sign(Algorithm.HMAC256(tokenProperties.getSecret()));
    }

    public Map<String, Claim> decodeToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenProperties.getSecret())).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    public <T> T getClaim(String token, String properties, Class<T> targetClass) {
        Map<String, Claim> claimMap = decodeToken(token);
        for (Map.Entry<String, Claim> entry : claimMap.entrySet()) {
            if (entry.getKey().equals(properties)) {
                return entry.getValue().as(targetClass);
            }
        }
        return null;
    }
    /**
     * 获取token负载中的数据
     * @param token token，用于解析
     * @param properties claim的key
     * @return 数据
     */
    public Object getClaim(String token, String properties) {
        return getClaim(token, properties, Object.class);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return 是否失效 true or false
     */
    public boolean isExpire(String token) {
        long current = System.currentTimeMillis();
        Long expire = getClaim(token, "exp", long.class);
        return expire - current > 0;
    }
}
