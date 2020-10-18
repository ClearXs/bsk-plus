package com.bsk.controller;

import com.bsk.common.controller.BaseController;
import com.bsk.common.modle.ApiResult;
import com.bsk.sys.auth.po.AuthPo;
import com.bsk.sys.auth.service.AuthService;
import com.bsk.sys.user.po.UserPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/auth")
@Api(value = "/auth", tags = "系统权限服务")
@Validated
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @PostMapping("/createAuth")
    @ApiOperation("创建权限")
    public ApiResult<String> createUser(@Validated AuthPo authPo) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            boolean success = authService.add(authPo);
            if (success) {
                apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "创建成功", "创建成功");
            } else {
                throw new Exception("创建失败");
            }
        } catch (UnauthorizedException e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), "当前用户没添加的权限", e);
            e.printStackTrace();
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }
}
