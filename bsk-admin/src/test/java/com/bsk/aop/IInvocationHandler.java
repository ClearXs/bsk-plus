package com.bsk.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IInvocationHandler implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    public IInvocationHandler(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    // 代理对象调用方法时执行invoke方法()
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object value = null;
        try {
            // 调用前置方法
            interceptor.before();
            // 判断是否使用环绕增强
            if (interceptor.userAround()) {
                value = interceptor.around(new Invocation(args, method, target));
            } else {
                value = method.invoke(target, args);
            }
            interceptor.afterReturning();
        } catch (Exception e) {
            interceptor.afterThrowing();;
        }
        // 调用后置
        interceptor.after();
        return value;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
