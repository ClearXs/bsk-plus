package com.bsk.sys.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bsk.BaseTest;
import com.bsk.common.constant.StringPool;
import com.bsk.sys.user.mapper.UserMapper;
import com.bsk.sys.user.po.UserPo;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.PAData;

import java.text.ParseException;
import java.util.Date;

public class WrapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("mobile");
        userMapper.selectList(wrapper);
    }

    @Test
    public void test2() throws ParseException {
        Page<UserPo> page = new Page<>(1, 2);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
//        wrapper.between("create_time", DateUtils.parseDate("2020-08-28 07:23:51", StringPool.DATE_FORMAT_DATETIME), DateUtils.parseDate("2020-08-29 15:47:23", StringPool.DATE_FORMAT_DATETIME)).
//                eq("password", "465");
        wrapper.between("create_time", "2020-08-28 07:23:51", "2020-08-29 15:47:23").
                eq("password", "465");
        IPage<UserPo> userPoIPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPoIPage);
    }

    @Test
    public void test3() {
        Page<UserPo> page = new Page<>(1, 2);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.like("mobile", "465");
        IPage<UserPo> userPoIPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPoIPage);
    }

    @Test
    public void test4() {
        Page<UserPo> page = new Page<>(1, 2);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.in("account", "1", "123");
        IPage<UserPo> userPoIPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPoIPage);
    }
}
