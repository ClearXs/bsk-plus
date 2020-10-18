package com.bsk.cache;

import com.bsk.cache.util.RedisUtils;

/**
 * redis缓存的实现
 * @author jiangw
 * @date 2020/9/8 16:47
 * @since 1.0
 */
public class RedisCache<T> implements ICache<T> {

    private RedisUtils redisUtils;

    public RedisCache(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public Boolean add(String key, T value, int timeOut) {
        return null;
    }

    @Override
    public T get(String key) {
        return null;
    }

    @Override
    public Boolean clearOne(String key) {
        return null;
    }

    @Override
    public Boolean clearAll() {
        return null;
    }

    @Override
    public Boolean update(String key, T value) {
        return null;
    }

    @Override
    public Boolean setIfAbsent(String key, T value) {
        return null;
    }
}
