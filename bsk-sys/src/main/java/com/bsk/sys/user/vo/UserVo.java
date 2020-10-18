package com.bsk.sys.user.vo;

import com.bsk.sys.auth.po.AuthPo;
import com.bsk.sys.role.po.RolePo;
import com.bsk.sys.user.po.UserPo;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApiModel("角色视图对象")
public class UserVo extends UserPo {

    private List<RolePo> rolePos;

    private List<AuthPo> authPos;

    public UserVo(List<RolePo> rolePos, List<AuthPo> authPos) {
        this.rolePos = rolePos;
        this.authPos = authPos;
    }

    public UserVo(UserPo userPo, List<RolePo> rolePos, List<AuthPo> authPos) {
        this.id = userPo.getId();
        this.account = userPo.getAccount();
        this.password = userPo.getPassword();
        this.email = userPo.getEmail();
        this.mobile = userPo.getMobile();
        this.createTime = userPo.getCreateTime();
        this.updateTime = userPo.getUpdateTime();
        this.version = userPo.getVersion();
        this.status = userPo.getStatus();
        this.deleted = userPo.getDeleted();
        this.rolePos = rolePos;
        this.authPos = authPos;
    }

    public List<RolePo> getRolePos() {
        return rolePos;
    }

    public void setRolePos(List<RolePo> rolePos) {
        this.rolePos = rolePos;
    }

    public List<AuthPo> getAuthPos() {
        return authPos;
    }

    public void setAuthPos(List<AuthPo> authPos) {
        this.authPos = authPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserVo userVo = (UserVo) o;
        return Objects.equals(rolePos, userVo.rolePos) &&
                Objects.equals(authPos, userVo.authPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rolePos, authPos);
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "rolePos=" + rolePos +
                ", authPos=" + authPos +
                '}';
    }
}
