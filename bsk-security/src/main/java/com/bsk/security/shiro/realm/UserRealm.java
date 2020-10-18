package com.bsk.security.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsk.common.util.BeanUtils;
import com.bsk.security.shiro.BskToken;
import com.bsk.security.shiro.authentication.UserAuthenticationInfo;
import com.bsk.sys.auth.po.AuthPo;
import com.bsk.sys.role.po.RolePo;
import com.bsk.sys.user.po.UserPo;
import com.bsk.sys.user.service.UserService;
import com.bsk.sys.user.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义用户身份验证与权限授权。
 * @author jiangw
 * @date 2020/9/7 14:20
 * @since 1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BskToken;
    }

    @Autowired
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 授权，查询当前用户所有的权限与角色
     * @author jiangw
     * @date 2020/9/4 18:20
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        try {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            UserPo userPo = new UserPo();
            userPo.setAccount(account);
            UserVo userVo = userService.getOne(userPo);
            List<AuthPo> authPos = userVo.getAuthPos();
            if (BeanUtils.isNotEmpty(authPos)) {
                // 获取当前用户所有的权限
                Set<String> perms = authPos.stream().map(AuthPo::getAlias).collect(Collectors.toSet());
                authorizationInfo.setStringPermissions(perms);
            }
            List<RolePo> rolePos = userVo.getRolePos();
            if (BeanUtils.isNotEmpty(rolePos)) {
                Set<String> roles = rolePos.stream().map(RolePo::getAlias).collect(Collectors.toSet());
                authorizationInfo.setRoles(roles);
            }
            return authorizationInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 身份验证
     * @author jiangw
     * @date 2020/9/4 18:21
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        BskToken usernamePasswordToken = (BskToken) token;
        String account = usernamePasswordToken.getAccount();
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        UserPo user = userService.getOne(wrapper);
        if (BeanUtils.isEmpty(user)) {
            throw new AccountException("账号错误");
        }
        return new UserAuthenticationInfo(String.valueOf(user.getStatus()), user, (String) token.getCredentials());
    }
}
