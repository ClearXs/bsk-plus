package com.bsk.common.support;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsk.common.util.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author jiangw
 * @date 2020/9/4 11:36
 * @since 1.0
 */
public class AbstractDataValidator<M extends BaseMapper<T>, T> implements DataValidator<M, T> {

    /**
     * mapper
     */
    private M mapper;

    /**
     * 调用目标mapper方法名称
     */
    private final String methodName;

    /**
     * 目标方法的参数
     */
    private final Object[] args;


    public AbstractDataValidator(M mapper, String methodName, Object... args) {
        this.mapper = mapper;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public boolean validate() throws InvocationTargetException, IllegalAccessException {
        T data = getData(mapper);
        return !BeanUtils.isNotEmpty(data);
    }

    @Override
    public T getData(M mapper) throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectUtil.getMethodByName(mapper.getClass(), methodName);
        return (T) method.invoke(mapper, args);
    }
}
