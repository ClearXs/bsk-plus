package com.bsk;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BaseTest {

    @Resource
    private IBean iBean;

    @Test
    public void test() throws Exception {
        FileReader fileReader = new FileReader();
        JSONObject meta = fileReader.getMetaByLayerId(null, 1);
        System.out.println(meta);
    }
}
