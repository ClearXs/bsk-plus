package com.bsk.thread;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

public class LocalTest {

    @Test
    public void test() {
        BaseLocal.set("123");
        LocalA localA = new LocalA();
        localA.get();
        ExecutorService executorService = ThreadUtil.newExecutor(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> System.out.println(BaseLocal.get()));
        }
        LocalB localB = new LocalB();
        localB.get();

    }
}
