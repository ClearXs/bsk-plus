package com.bsk.aop;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsk.BaseTest;
import com.bsk.sys.user.po.UserPo;
import com.bsk.sys.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AopTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
        IService service = new IServiceImpl();
        IService proxyBean = (IService) ProxyUtil.getProxyBean(service, new IInterceptor());
        proxyBean.sayHello();
        // 总结：通过jdk的动态代理，实现aop功能，其实先为在调用代理对象方法时，传入我们的切面接口，通过不同的实现来执行对应的方法
    }

    @Test
    public void test2() {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("account", "123");
        UserPo one = userService.getOne(wrapper);
        System.out.println(one);
    }
}
