package com.bsk.sys.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsk.sys.user.mapper.UserRoleMapper;
import com.bsk.sys.user.po.UserRolePo;
import com.bsk.sys.user.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRolePo> implements UserRoleService {
}
