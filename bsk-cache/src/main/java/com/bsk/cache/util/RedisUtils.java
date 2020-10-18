package com.bsk.cache.util;

import com.bsk.common.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    @Qualifier("bskRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    // ===================common=====================

    /**
     * 设置键过期时间
     * @param key 插入的主键
     * @param timeOut 过期时间，单位为秒
     * @return true 成功， false 失败
     * @date 2020/9/6 15:37
     */
    public Boolean setExpire(String key, long timeOut) {
        if (BeanUtils.isEmpty(key) || BeanUtils.isEmpty(timeOut)) {
            throw new RuntimeException("key或者过期时间为空");
        }
        return redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     * @param key 插入的主键
     * @param timeOut 过期时间，单位为秒
     * @param timeUnit 时间单位
     * @return true 成功， false 失败
     * @date 2020/9/6 15:37
     */
    public Boolean setExpire(String key, long timeOut, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 插入是设置过期时间，默认是毫秒
     * @param key 插入的主键
     * @param value 插入的值
     * @param timeOut 过期时间，单位为秒
     * @return true 成功， false 失败
     */
    public Boolean setExpire(String key, Object value, long timeOut) {
        set(key, value);
        return setExpire(key, timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置String类型的键值
     * @author jiangw
     * @param key 键
     * @param value 值
     * @date 2020/9/6 15:42
     */
    public void set(String key, Object value) {
        if (BeanUtils.isEmpty(key)) {
            throw new RuntimeException("key为空");
        }

        if (BeanUtils.isEmpty(value)) {
            throw new RuntimeException("value为空");
        }
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @author jiangw
     * @param key 键
     * @return 返回该键获取的值
     * @date 2020/9/6 15:43
     */
    public Object get(String key) {
        if (BeanUtils.isEmpty(key)) {
            throw new RuntimeException("key为空");
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断键是否存在
     * @param key 键
     * @return true 存在， false 不存在
     */
    public Boolean isExits(String key) {
        return redisTemplate.hasKey(key);
    }
}
