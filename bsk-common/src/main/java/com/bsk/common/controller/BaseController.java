package com.bsk.common.controller;

import com.bsk.common.modle.ApiResult;

/**
 * 基础controller
 * @author jiangw
 * @date 2020/8/28 11:24
 * @since 1.0
 */
public abstract class BaseController {

    protected <T> ApiResult<T> getExceptionResult(ApiResult<T> apiResult, int state, Exception e) {
        return getExceptionResult(apiResult, state, e.getMessage(), e);
    }

    protected <T> ApiResult<T> getExceptionResult(ApiResult<T> apiResult, int state, String message, Exception e) {
        apiResult.setState(state);
        apiResult.setMessage(message);
        apiResult.setCause(e.getMessage());
        return apiResult;
    }

    protected <T> ApiResult<T> getApiResult(ApiResult<T> apiResult, int state, String message, T data) {
        apiResult.setState(state);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }
}
