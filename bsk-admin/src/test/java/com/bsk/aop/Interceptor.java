package com.bsk.aop;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    // 事件调用前
    boolean before();

    // 事件调用后
    void after();

    // 是否
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    boolean userAround();

    // 事件没有异常调用
    void afterReturning();

    // 事件发生异常调用
    void afterThrowing();
}
