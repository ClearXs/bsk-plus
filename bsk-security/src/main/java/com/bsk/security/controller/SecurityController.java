package com.bsk.security.controller;

import com.bsk.common.controller.BaseController;
import com.bsk.common.modle.ApiResult;
import com.bsk.security.shiro.BskToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/api/security", tags = "安全服务")
@RequestMapping("/api/security")
public class SecurityController extends BaseController {

    @PostMapping("/login")
    @ApiOperation("登录")
    public ApiResult<String> login(
            @ApiParam(value = "用户名", required = true) @RequestParam(name = "username") String username,
            @ApiParam(value = "密码", required = true) @RequestParam(name = "password") String password,
            @ApiParam(value = "记住我", required = false) @RequestParam(name = "rememberMe") Boolean rememberMe) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            BskToken token = new BskToken(username, password);
            token.setRememberMe(rememberMe);
            subject.login(token);
            Session session = subject.getSession();
            apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "登录成功", (String) session.getAttribute("token"));
        } catch (AccountException | IncorrectCredentialsException e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.FORBIDDEN.value(), e);
        }
        return apiResult;
    }
}
