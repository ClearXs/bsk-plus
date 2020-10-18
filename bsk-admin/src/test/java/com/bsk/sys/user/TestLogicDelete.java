package com.bsk.sys.user;

import com.bsk.BaseTest;
import com.bsk.sys.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestLogicDelete extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.selectById("1299289586375839746");
        userMapper.deleteById("1299289586375839746");
    }
}
