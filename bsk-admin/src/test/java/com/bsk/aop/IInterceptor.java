package com.bsk.aop;

import java.lang.reflect.InvocationTargetException;

public class IInterceptor implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("执行前置方法");
        return true;
    }

    @Override
    public void after() {
        System.out.println("执行后之方法");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        return invocation.proceed();
    }

    @Override
    public boolean userAround() {
        return true;
    }

    @Override
    public void afterReturning() {
        System.out.println("后处理");
    }

    @Override
    public void afterThrowing() {
        System.out.println("处理异常");
    }
}
