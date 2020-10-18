package com.bsk.sys.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsk.common.constant.StringPool;
import com.bsk.common.support.AbstractDataValidator;
import com.bsk.common.support.ValidatorContext;
import com.bsk.common.util.BeanUtils;
import com.bsk.sys.auth.po.AuthPo;
import com.bsk.sys.auth.service.AuthService;
import com.bsk.sys.role.po.RolePo;
import com.bsk.sys.role.service.RoleService;
import com.bsk.sys.user.mapper.UserMapper;
import com.bsk.sys.user.po.UserPo;
import com.bsk.sys.user.po.UserRolePo;
import com.bsk.sys.user.service.UserRoleService;
import com.bsk.sys.user.service.UserService;
import com.bsk.sys.user.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean add(UserPo userPo) throws Exception {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("account", userPo.getAccount());
        ValidatorContext validatorContext = new ValidatorContext(new AbstractDataValidator<>(userMapper, "selectList", wrapper));
        boolean validate = validatorContext.execute();
        if (!validate) {
            throw new Exception("用户已经存在");
        }
        // 密码进行散列加密 salt为用户名+bsk
        Md5Hash md5Hash = new Md5Hash(userPo.getPassword(), userPo.getAccount() + StringPool.SALT);
        userPo.setPassword(md5Hash.toHex());
        return save(userPo);
    }

    @Override
    public boolean edit(UserPo userPo) {
        return false;
    }

    @Override
    public UserVo getOne(UserPo userPo) throws Exception {
        QueryWrapper<UserPo> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", userPo.getAccount());
        UserPo user = getOne(userQueryWrapper);
        if (BeanUtils.isEmpty(user)) {
            throw new Exception("无法查找用户");
        }

        QueryWrapper<UserRolePo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        List<UserRolePo> userRolePos = userRoleService.list(wrapper);

        // 查角色
        List<RolePo> rolePos = CollectionUtil.newArrayList();
        userRolePos.forEach(userRolePo -> {
            QueryWrapper<RolePo> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("id", userRolePo.getId());
            roleService.list(roleQueryWrapper);
        });
        // 查权限
        List<AuthPo> authPos = CollectionUtil.newArrayList();
        userRolePos.forEach(userRolePo -> {
            QueryWrapper<AuthPo> authQueryWrapper = new QueryWrapper<>();
            authQueryWrapper.eq("role_id", userRolePo.getId());
            authPos.addAll(authService.list(authQueryWrapper));
        });
        return new UserVo(user, rolePos, authPos);
    }

    @Override
    public List<UserVo> getAll() {
        List<UserPo> userPos = list();
        List<UserVo> userVos = CollectionUtil.newArrayList();
        userPos.forEach(userPo -> {
            try {
                userVos.add(getOne(userPo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return userVos;
    }
}
