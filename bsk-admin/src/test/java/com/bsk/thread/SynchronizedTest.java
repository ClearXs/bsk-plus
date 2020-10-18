package com.bsk.thread;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest {

    private static boolean isStop = false;

    static synchronized void requestStop() {
        isStop = true;
    }

    static synchronized boolean isStop() {
        return isStop;
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = ThreadUtil.newExecutor(2);
        for (int i = 0; i < 2; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    while (!isStop()) {
                        System.out.println(Thread.currentThread().getName() + "-" + i++);
                    }
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);
        requestStop();
    }

    @Test
    public void test2() throws InterruptedException {
        NonReentrantLock lock = new NonReentrantLock();
        lock.lock();
        doAdd(lock);
        lock.unlock();
    }

    @Test
    public void test3() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doAdd(lock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        lock.unlock();
    }

    static void doAdd(Lock lock) throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        lock.unlock();
    }

    interface Lock {
        void lock() throws InterruptedException;

        void unlock();
    }


    class NonReentrantLock implements Lock {
        private boolean isLocked = false;

        @Override
        public synchronized void lock() throws InterruptedException {
            while (isLocked) {
                wait();
            }
            isLocked = true;
        }

        @Override
        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }

    // 实现原理是通过为每个锁关联一个请求计数器和一个占有它的线程。当计数为0时，认为锁是未被占有的；线程请求一个未被占有的锁时，JVM将记录锁的占有者，并且将请求计数器置为1
    class ReentrantLock implements Lock {
        private boolean isLocked = false;

        private Thread lockedBy = null;

        private int lockedCount = 0;

        @Override
        public synchronized void lock() throws InterruptedException {
            Thread thread = Thread.currentThread();
            // 锁住并且当前获取锁的现场不是当前线程时，当前线程则被锁住
            while (isLocked && lockedBy != thread) {
                wait();
            }
            isLocked = true;
            lockedBy = thread;
            lockedCount++;
        }

        @Override
        public synchronized void unlock() {
            if (Thread.currentThread() == lockedBy) {
                lockedCount--;
                if (lockedCount == 0) {
                    isLocked = false;
                    notify();
                }
            }
        }

    }
}
