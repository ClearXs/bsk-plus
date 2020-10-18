package com.bsk.sys.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsk.sys.role.mapper.RoleMapper;
import com.bsk.sys.role.po.RolePo;
import com.bsk.sys.role.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePo> implements RoleService {
}
