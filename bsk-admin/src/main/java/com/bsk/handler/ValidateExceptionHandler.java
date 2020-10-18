package com.bsk.handler;

import com.bsk.common.controller.BaseController;
import com.bsk.common.modle.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 验证异常全局处理器，即监听MethodArgumentNotValidException异常
 * 使用切面处理
 * @author jiangw
 * @date 2020/9/5 0:09
 * @since 1.0
 */
@RestControllerAdvice
public class ValidateExceptionHandler extends BaseController {

    @ExceptionHandler(BindException.class)
    public ApiResult<List<String>> validateErrorHandler(BindException ex) {
        List<String> errors = ex.getAllErrors().
                stream().
                map(ObjectError::getDefaultMessage).
                collect(Collectors.toList());
        ApiResult<List<String>> apiResult = new ApiResult<>();
        return getApiResult(apiResult, HttpStatus.BAD_REQUEST.value(), "参数验证失败", errors);
    }
}
