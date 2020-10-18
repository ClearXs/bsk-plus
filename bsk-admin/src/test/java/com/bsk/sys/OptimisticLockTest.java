package com.bsk.sys;

import com.bsk.BaseTest;
import com.bsk.sys.user.mapper.UserMapper;
import com.bsk.sys.user.po.UserPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OptimisticLockTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        // 1.查找用户
        UserPo user = userMapper.selectById("1299246290848841729");

        // 2.修改
        user.setAccount("12312");
        // 3.更新
        userMapper.updateById(user);

    }

    @Test
    public synchronized void testConcurrency() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 1.查找用户
                UserPo user = userMapper.selectById("1299246290848841729");
                // 2.修改
                user.setAccount("1");
                // 3.更新
                userMapper.updateById(user);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1.查找用户
                UserPo user = userMapper.selectById("1299246290848841729");
                // 2.修改
                user.setAccount("2");
                // 3.更新
                userMapper.updateById(user);
                notify();
            }
        }).start();
        Thread.sleep(12000);
    }
}
