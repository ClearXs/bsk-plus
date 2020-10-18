package com.bsk.sys.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bsk.BaseTest;
import com.bsk.sys.user.mapper.UserMapper;
import com.bsk.sys.user.po.UserPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestPage extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        Page<UserPo> page = new Page<>(1, 2);
        IPage<UserPo> userPoIPage = userMapper.selectPage(page, null);

        List<UserPo> records = userPoIPage.getRecords();
        records.forEach(System.out::println);
    }
}
