package com.bsk.sys.user;

import com.bsk.BaseTest;
import com.bsk.sys.user.service.UserService;
import com.bsk.sys.user.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestGetUser extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        List<UserVo> userVos = userService.getAll();
        System.out.println(userVos);
    }
}
