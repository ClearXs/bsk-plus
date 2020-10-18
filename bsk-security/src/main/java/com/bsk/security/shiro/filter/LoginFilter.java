package com.bsk.security.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 处理登录的过滤器
 * 1.登录成功的响应
 * 2.登录失败的响应
 * @author jiangw
 * @date 2020/9/20 15:51
 * @since 1.0
 */
public class LoginFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }
}
