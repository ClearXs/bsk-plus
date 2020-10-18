package com.bsk.controller;

import com.bsk.common.controller.BaseController;
import com.bsk.common.modle.ApiResult;
import com.bsk.sys.role.po.RolePo;
import com.bsk.sys.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
@Validated
@Api(value = "/sys/role", tags = "系统角色服务")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ApiResult<String> createRole(@Validated RolePo rolePo) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            boolean success = roleService.save(rolePo);
            if (success) {
                apiResult = getApiResult(apiResult, HttpStatus.OK.value(), "添加角色成功", "添加角色成功");
            } else {
                throw new Exception("添加角色失败");
            }
        } catch (Exception e) {
            apiResult = getExceptionResult(apiResult, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
            e.printStackTrace();
        }
        return apiResult;
    }
}
