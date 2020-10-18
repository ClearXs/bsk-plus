package com.bsk.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 普通的内存缓存，采用concurrentHashMap实现
 * @author jiangw
 * @date 2020/9/8 11:52
 * @since 1.0
 */
public class MemoryCache<T> implements ICache<T> {

    private final static int DEFAULT_SIZE = 16;

    private final Map<String, T> cache;

    public MemoryCache() {
        cache = new ConcurrentHashMap<>(DEFAULT_SIZE);
    }

    public MemoryCache(int size) {
        cache = new ConcurrentHashMap<>(size);
    }

    @Override
    public Boolean add(String key, T value, int timeOut) {
         cache.put(key, value);
         return true;
    }

    @Override
    public T get(String key) {
        return cache.get(key);
    }

    @Override
    public Boolean clearOne(String key) {
        cache.remove(key);
        return true;
    }

    @Override
    public Boolean clearAll() {
        cache.clear();
        return true;
    }

    @Override
    public Boolean update(String key, T value) {
        cache.put(key, value);
        return true;
    }

    @Override
    public Boolean setIfAbsent(String key, T value) {
        boolean contains = cache.containsKey(key);
        if (contains) {
            return false;
        }
        cache.put(key, value);
        return true;
    }
}
