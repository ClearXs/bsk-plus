package com.bsk.security.shiro.authentication;

import com.bsk.sys.user.po.UserPo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * 用户认证的信息
 * @author jiangw
 * @date 2020/9/7 15:53
 * @since 1.0
 */
public class UserAuthenticationInfo implements AuthenticationInfo {

    private String realmName;

    private UserPo userPo;

    private String token;

    public UserAuthenticationInfo() {
    }

    public UserAuthenticationInfo(String realmName, UserPo userPo, String token) {
        this.realmName = realmName;
        this.userPo = userPo;
        this.token = token;
    }

    /**
     * 身份的主体，可以是任何东西，
     * 但一般为标识的账号/邮箱/手机号，可以包含多个Principals
     * @author jiangw
     * @date 2020/9/7 15:54
     */
    @Override
    public PrincipalCollection getPrincipals() {
        SimplePrincipalCollection collection = new SimplePrincipalCollection();
        collection.add(userPo.getAccount(), realmName);
        collection.add("token", token);
        return collection;
    }

    /**
     * 验证证书，应是一个唯一标识
     * @author jiangw
     * @date 2020/9/7 15:54
     */
    @Override
    public Object getCredentials() {
        return token;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public UserPo getUserPo() {
        return userPo;
    }

    public void setUserVo(UserPo userPo) {
        this.userPo = userPo;
    }
}
