package com.bsk.sys.role.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("角色权限关联实体")
@TableName("sys_role_auth")
public class RoleAuthPo {

    @ApiModelProperty("id")
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("权限id")
    private String authId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("乐观锁")
    @Version
    private int version;
}
