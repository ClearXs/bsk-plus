package com.bsk.cache;

import com.bsk.BaseTest;
import com.bsk.cache.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilsTest extends BaseTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        redisUtils.set("123", "213");
        System.out.println(redisUtils.get("123"));
    }

}
