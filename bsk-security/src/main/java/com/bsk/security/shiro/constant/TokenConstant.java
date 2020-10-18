package com.bsk.security.shiro.constant;

/**
 * 记录一些token的常量
 * @author jiangw
 * @date 2020/9/17 20:56
 * @since 1.0
 */
public interface TokenConstant {

    /**
     * header请求头token key
     */
    String ACCESS_TOKEN = "X-Authorization-access_token";

    /**
     * claim 负载中的账号
     */
    String ACCOUNT = "account";

    /**
     * claim 负载中的密码
     */
    String PASSWORD = "password";

    /**
     * claim 负载中的rememberMe
     */
    String REMEMBER_ME = "rememberMe";

    /**
     * claim token访问的时间戳，若token的时间戳与系统存放时间戳一直才能进行访问，否则被拦截（主要用于重复提交）
     */
    String ACCESS_TIMESTAMP = "timestamp";
}
