package com.bsk.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

public class PermissionUtils {

    public void checkPermission(String perm) {
        Subject subject = SecurityUtils.getSubject();
        // 检验当前是否具有管理员权限
        subject.checkPermission(perm);
    }

    public void checkPermission(Permission permission) {

    }
}
