package com.bsk.common.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.lang.reflect.InvocationTargetException;

/**
 * 数据验证器，主要用于验证数据库中某张表目标值是否存在，具体由每个实体实现
 * @author jiangw
 * @date 2020/9/4 10:08
 * @since 1.0
 */
public interface DataValidator<M extends BaseMapper<T>, T> extends Validator {

    /**
     * 通过mapper获取目标实体的数据
     * @author jiangw
     * @date 2020/9/4 10:21
     */
    T getData(M t) throws InvocationTargetException, IllegalAccessException;

}
