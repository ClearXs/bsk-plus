package com.bsk.thread;

public class LocalA {

    public void get() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(BaseLocal.get());
    }
}
