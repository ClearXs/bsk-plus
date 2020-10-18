package com.bsk.security.util;

import com.bsk.sys.user.po.UserPo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ContextUtils {

    public static UserPo getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        UserPo userPo = (UserPo) subject.getPrincipal();
        return userPo;
    }


}
