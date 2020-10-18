package com.bsk.sys.role.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

@ApiModel("角色")
@TableName("sys_role")
public class RolePo {

    @ApiModelProperty("id")
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色别名")
    private String alias;

    @ApiModelProperty("角色注解")
    private String note;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("版本号")
    @Version
    private int version;

    @ApiModelProperty("删除标识")
    @TableLogic
    private int deleted;

    public RolePo() {
    }

    public RolePo(String id, String name, String alias, String note, Date createTime, Date updateTime, int version, int deleted) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.note = note;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.deleted = deleted;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RolePo rolePo = (RolePo) o;
        return version == rolePo.version &&
                deleted == rolePo.deleted &&
                Objects.equals(id, rolePo.id) &&
                Objects.equals(name, rolePo.name) &&
                Objects.equals(alias, rolePo.alias) &&
                Objects.equals(note, rolePo.note) &&
                Objects.equals(createTime, rolePo.createTime) &&
                Objects.equals(updateTime, rolePo.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alias, note, createTime, updateTime, version, deleted);
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", deleted=" + deleted +
                '}';
    }
}
