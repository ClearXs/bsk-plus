package com.bsk.aop;

import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static Object getProxyBean(Object target, Interceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new IInvocationHandler(target, interceptor));
    }
}
