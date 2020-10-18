package com.bsk.sys.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsk.sys.role.mapper.RoleAuthMapper;
import com.bsk.sys.role.po.RoleAuthPo;
import com.bsk.sys.role.service.RoleAuthService;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuthPo> implements RoleAuthService {
}
