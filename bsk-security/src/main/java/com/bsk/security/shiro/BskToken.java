package com.bsk.security.shiro;

import com.bsk.common.util.AppUtils;
import com.bsk.security.util.TokenUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 根据账号/密码生成唯一的token
 * @author jiangw
 * @date 2020/9/16 23:00
 * @since 1.0
 */
public class BskToken implements AuthenticationToken {

    private String account;

    private String password;

    private boolean rememberMe;

    private String host;

    public BskToken(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public BskToken(String account, String password, boolean rememberMe) {
        this.account = account;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public BskToken(String account, String password, boolean rememberMe, String host) {
        this.account = account;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }

    public boolean isRememberMe() {
        return this.rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return this.account;
    }

    /**
     * 证书，这里使用jwtToken生成
     */
    @Override
    public Object getCredentials() {
        TokenUtils tokenUtils = (TokenUtils) AppUtils.getBean(TokenUtils.class);
        return tokenUtils.createToken(getAccount(), getPassword(), isRememberMe());
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
