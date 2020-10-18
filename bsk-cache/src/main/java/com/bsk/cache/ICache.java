package com.bsk.cache;

/**
 * 一个缓存的通用接口，主要进行一些常用的操作
 * @author jiangw
 * @date 2020/9/8 11:44
 * @since 1.0
 */
public interface ICache<T> {

    /**
     * 缓存中添加值
     * @author jiangw
     * @param key 键
     * @param value 值
     * @param timeOut 过期时间
     * @return 返回是否成功
     * @date 2020/9/8 11:45
     */
    Boolean add(String key, T value, int timeOut);

    /**
     * 根据key获取value
     * @author jiangw
     * @param key 键
     * @return 返回该键的对象
     * @date 2020/9/8 11:47
     */
    T get(String key);

    /**
     * 根据键删除值
     * @author jiangw
     * @param key 键
     * @return 是否成功
     * @date 2020/9/8 11:48
     */
    Boolean clearOne(String key);

    /**
     * 清除全部
     * @author jiangw
     * @return 是否成功
     * @date 2020/9/8 11:50
     */
    Boolean clearAll();

    /**
     * 更新值
     * @author jiangw
     * @param key 键
     * @param value 值
     * @return 是否成功
     * @date 2020/9/8 11:50
     */
    Boolean update(String key, T value);

    /**
     * 如果成功则不设置值
     * @author jiangw
     * @param key 键
     * @param value 值
     * @return 返回是否成功
     * @date 2020/9/8 16:43
     */
    Boolean setIfAbsent(String key, T value);

}
