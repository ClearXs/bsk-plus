package com.bsk.sys.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsk.sys.auth.po.AuthPo;

public interface AuthService extends IService<AuthPo> {

    boolean add(AuthPo authPo);
}
