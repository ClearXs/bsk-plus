package com.bsk.sys.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsk.sys.auth.po.AuthPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper extends BaseMapper<AuthPo> {
}
