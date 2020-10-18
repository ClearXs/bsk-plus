package com.bsk.security.shiro.matcher;

import com.auth0.jwt.interfaces.Claim;
import com.bsk.common.constant.StringPool;
import com.bsk.common.util.AppUtils;
import com.bsk.common.util.BeanUtils;
import com.bsk.security.properties.AuthProperties;
import com.bsk.security.shiro.authentication.UserAuthenticationInfo;
import com.bsk.security.shiro.exception.DeleteAccountException;
import com.bsk.security.util.TokenUtils;
import com.bsk.sys.user.constant.UserStatus;
import com.bsk.sys.user.po.UserPo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户重试校验
 * @author jiangw
 * @date 2020/9/7 14:42
 * @since 1.0
 */
@Component
public class RetryLimitCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 验证密码，错误此时超过配置值，用户上锁
     * @author jiangw
     * @date 2020/9/7 15:36
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Subject subject = SecurityUtils.getSubject();
        AuthProperties property = (AuthProperties) AppUtils.getProperty(AuthProperties.class);
        // 验证码重试次数
        int validRetry = property.getValidate().getRetry();
        UserAuthenticationInfo shiroInfo = (UserAuthenticationInfo) info;
        subject.getSession().setAttribute("token", token.getCredentials());
        Map<String, Claim> claimMap = tokenUtils.decodeToken((String) token.getCredentials());
        String encryptionPassword = new Md5Hash(claimMap.get("password").asString(), token.getPrincipal() + StringPool.SALT).toHex();
        UserPo user = shiroInfo.getUserPo();
        // 验证当前用户状态
        switch (user.getStatus()) {
            case UserStatus.ACTIVATED:
                if (!BeanUtils.equal(user.getPassword(), encryptionPassword)) {
                    throw new IncorrectCredentialsException("密码错误");
                }
                // redis存储当前用户

                break;
            case UserStatus.DELETED:
                throw new DeleteAccountException(user.getAccount() + ": 账号被删除");
            case UserStatus.STOP:
                throw new DisabledAccountException(user.getAccount() + ": 账号停用");
            case UserStatus.LOCK:
                throw new LockedAccountException(user.getAccount() + ": 账号被锁住");
            default:
                throw new AuthenticationException("身份验证失败");
        }
        // 验证当前用户密码错误次数
        return true;
    }
}
