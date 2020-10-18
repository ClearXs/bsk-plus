package com.bsk.thread;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock(true);

    @Test
    public void test() {
        ExecutorService executor = ThreadUtil.newExecutor(5);
        for (int i= 0 ; i < 5; i++) {
            executor.execute(ReentrantLockTest::fair);
        }
    }

    public static synchronized void unfair() {
        System.out.println(Thread.currentThread().getName() + "获得了锁");
    }

    public static void fair() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得的了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
