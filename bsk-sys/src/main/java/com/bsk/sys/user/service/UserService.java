package com.bsk.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bsk.sys.user.po.UserPo;
import com.bsk.sys.user.vo.UserVo;

import java.util.List;


public interface UserService extends IService<UserPo> {

    /**
     * 添加用户
     * @author jiangw
     * @date 2020/8/28 19:30
     */
    boolean add(UserPo userPo) throws Exception;

    /**
     * 修改用户
     * @author jiangw
     * @date 2020/8/28 19:30
     */
    boolean edit(UserPo userPo);

    /**
     * 获取单个用户
     * @author jiangw
     * @date 2020/9/4 16:21
     */
    UserVo getOne(UserPo userPo) throws Exception;

    /**
     * 获取所有的用户
     * @author jiangw
     * @date 2020/9/4 16:20
     */
    List<UserVo> getAll();

}
