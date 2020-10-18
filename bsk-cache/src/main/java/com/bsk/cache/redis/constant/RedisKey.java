package com.bsk.cache.redis.constant;

/**
 * 一些常用的redis key
 * @author jiangw
 * @date 2020/9/17 22:33
 * @since 1.0
 */
public interface RedisKey {

    /**
     * 所有token的前缀
     */
    String PREFIX = "bsk";

    /**
     * 刷新访问token的key 
     */
    String REFRESH_ACCESS_TOKEN_KEY = "refreshAccessToken";

    /**
     * token白名单
     */
    String TOKEN_WHITE_LIST = "tokenWhiteList";

    /**
     * token黑名单
     */
    String TOKEN_BLACK_LIST = "tokenBlackList";
}
