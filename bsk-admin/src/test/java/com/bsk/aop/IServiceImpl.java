package com.bsk.aop;

public class IServiceImpl implements IService {

    @Override
    public void sayHello() throws Exception {
        System.out.println("sayHello");
        throw new Exception("throwing");
    }
}
