package com.bsk.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsk.sys.user.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {
}
