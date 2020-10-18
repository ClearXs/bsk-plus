package com.bsk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bsk.common.controller.BaseController;
import com.bsk.common.modle.ApiResult;
import com.bsk.security.shiro.constant.TokenConstant;
import com.bsk.security.util.TokenUtils;
import com.bsk.sys.user.constant.UserStatus;
import com.bsk.sys.user.po.UserPo;
import com.bsk.sys.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/user")
@Api(value = "/user", tags = "系统用户服务")
@Validated
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping("/createUser")
    @ApiOperation("创建用户")
    public ApiResult<String> createUser(@Validated UserPo userPo) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            boolean success = userService.add(userPo);
            if (success) {
                apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "创建成功", "创建成功");
            } else {
                throw new Exception("创建失败");
            }
        } catch (UnauthorizedException e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), "当前用户没添加用户的权限", e);
            e.printStackTrace();
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户")
    public ApiResult<String> updateUser(
            @ApiParam(value = "id", required = true) @RequestParam(name = "password") String id,
            @ApiParam(value = "密码") @RequestParam(name = "password", required = false) String password,
            @ApiParam(value = "邮箱") @RequestParam(name = "email", required = false) String email,
            @ApiParam(value = "联系电话") @RequestParam(name = "mobile", required = false) String mobile) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            UserPo userPo = new UserPo();
            userPo.setPassword(password);
            userPo.setEmail(email);
            userPo.setMobile(mobile);
            boolean success = userService.update(userPo, null);
            if (success) {
                apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "更新失败", "更新失败");
            } else {
                throw new Exception("更新失败");
            }
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

    @PostMapping("/deleteUser")
    @ApiOperation("删除用户")
    public ApiResult<String> updateUser(
            @ApiParam(value = "用户id", required = true) @RequestParam(name = "id") String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            UserPo userPo = new UserPo();
            userPo.setStatus(UserStatus.DELETED);
            UpdateWrapper<UserPo> wrapper = new UpdateWrapper<>();
            boolean success = userService.update(userPo, null);
            if (success) {
                apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "删除成功", "删除成功");
            } else {
                throw new Exception("删除失败");
            }
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

    @GetMapping("/queryUserById")
    @ApiOperation("根据id查询用户")
    public ApiResult<UserPo> queryUserById(
            @ApiParam(value = "用户id", required = true) @RequestParam(name = "id") String id) {
        ApiResult<UserPo> apiResult = new ApiResult<>();
        try {
            QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            UserPo userPo = userService.getOne(wrapper);
            apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "查询成功", userPo);
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

    @GetMapping("/queryUserByAccount")
    @ApiOperation("根据账号查询用户")
    public ApiResult<UserPo> queryUserByAccount(
            @ApiParam(value = "用户账号", required = true) @RequestParam(name = "account") String account) {
        ApiResult<UserPo> apiResult = new ApiResult<>();
        try {
            QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
            wrapper.eq("account", account);
            UserPo userPo = userService.getOne(wrapper);
            apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "查询成功", userPo);
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

    @GetMapping("/queryUserByToken")
    @ApiOperation("根据Token查询用户")
    public ApiResult<UserPo> queryUserByAccount(HttpServletRequest request) {
        ApiResult<UserPo> apiResult = new ApiResult<>();
        String token = request.getHeader(TokenConstant.ACCESS_TOKEN);
        try {
            String account = tokenUtils.getClaim(token, TokenConstant.ACCOUNT, String.class);
            QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
            wrapper.eq("account", account);
            UserPo userPo = userService.getOne(wrapper);
            apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "查询成功", userPo);
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }

}
