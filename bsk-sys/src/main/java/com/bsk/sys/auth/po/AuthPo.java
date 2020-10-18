package com.bsk.sys.auth.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@ApiModel("角色")
@TableName("sys_auth")
public class AuthPo {

    @ApiModelProperty("id")
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty("名称")
    @NotNull
    private String name;

    @ApiModelProperty("别名")
    @NotNull
    private String alias;

    @ApiModelProperty("权限的类型，菜单/页面/按钮")
    @NotNull
    private String type;

    @ApiModelProperty("描述")
    private String des;

    @ApiModelProperty("乐观锁")
    @Version
    private int version;

    @ApiModelProperty("删除标识")
    @TableLogic
    private int deleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public AuthPo() {
    }

    public AuthPo(String id, String name, String alias, String type, String des, int version, int deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.type = type;
        this.des = des;
        this.version = version;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthPo authPo = (AuthPo) o;
        return version == authPo.version &&
                deleted == authPo.deleted &&
                Objects.equals(id, authPo.id) &&
                Objects.equals(name, authPo.name) &&
                Objects.equals(alias, authPo.alias) &&
                Objects.equals(type, authPo.type) &&
                Objects.equals(des, authPo.des) &&
                Objects.equals(createTime, authPo.createTime) &&
                Objects.equals(updateTime, authPo.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alias, type, des, version, deleted, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "AuthPo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                ", des='" + des + '\'' +
                ", version=" + version +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
