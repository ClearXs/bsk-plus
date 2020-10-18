package com.bsk.security.properties;

import com.bsk.BaseTest;
import com.bsk.common.util.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigTest extends BaseTest {

    @Autowired
    private AuthProperties authConfig;

    @Test
    public void test() {
        Object property = AppUtils.getProperty(AuthProperties.class);
        System.out.println(property == authConfig);
        System.out.println(authConfig);
    }
}
