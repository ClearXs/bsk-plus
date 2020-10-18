package com.bsk.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocation {

    private Object[] objects;

    private Method method;

    // 原始对象
    private Object target;

    public Invocation(Object[] objects, Method method, Object target) {
        this.objects = objects;
        this.method = method;
        this.target = target;
    }

    // 反射执行目标对象的方法
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, objects);
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
