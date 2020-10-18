package com.bsk.thread;

import cn.hutool.core.thread.ThreadUtil;
import com.bsk.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest extends BaseTest {

    static class Custom implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(executor);
        }
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    @Test
    public void test() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                ThreadUtil.newNamedThreadFactory("jw", false),
                new Custom());
        for (int i = 0; i < 7; i++) {
            MyTask myTask = new MyTask();
            executor.submit(myTask);
        }
    }
}
