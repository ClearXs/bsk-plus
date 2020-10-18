package com.bsk.sys.user.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@ApiModel("用户")
@TableName("sys_user")
public class UserPo {

    @ApiModelProperty("id")
    @TableId(type = IdType.ID_WORKER_STR)
    protected String id;

    @ApiModelProperty("账号")
    @NotNull(message = "账号不能为空")
    protected String account;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    protected String password;

    @ApiModelProperty("邮箱")
    protected String email;

    @ApiModelProperty("电话号码")
    protected String mobile;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    @ApiModelProperty("版本号")
    @Version
    protected int version;

    @ApiModelProperty("用户状态")
    protected int status;

    @ApiModelProperty("删除记录标识")
    @TableLogic
    protected int deleted;

    @ApiModelProperty("是否是管理员")
    private int isSuper;

    public UserPo() {
    }

    public UserPo(String id, @NotNull(message = "账号不能为空") String account, @NotNull(message = "密码不能为空") String password, String email, String mobile, Date createTime, Date updateTime, int version, int status, int deleted, int isSuper) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.status = status;
        this.deleted = deleted;
        this.isSuper = isSuper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(int isSuper) {
        this.isSuper = isSuper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPo userPo = (UserPo) o;
        return version == userPo.version &&
                status == userPo.status &&
                deleted == userPo.deleted &&
                isSuper == userPo.isSuper &&
                Objects.equals(id, userPo.id) &&
                Objects.equals(account, userPo.account) &&
                Objects.equals(password, userPo.password) &&
                Objects.equals(email, userPo.email) &&
                Objects.equals(mobile, userPo.mobile) &&
                Objects.equals(createTime, userPo.createTime) &&
                Objects.equals(updateTime, userPo.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password, email, mobile, createTime, updateTime, version, status, deleted, isSuper);
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", status=" + status +
                ", deleted=" + deleted +
                ", isSuper=" + isSuper +
                '}';
    }
}
