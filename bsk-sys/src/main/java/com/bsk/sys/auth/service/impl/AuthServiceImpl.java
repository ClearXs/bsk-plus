package com.bsk.sys.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bsk.sys.auth.mapper.AuthMapper;
import com.bsk.sys.auth.po.AuthPo;
import com.bsk.sys.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, AuthPo> implements AuthService {

    @Override
    public boolean add(AuthPo authPo) {

        return false;
    }
}
