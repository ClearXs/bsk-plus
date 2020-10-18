package com.bsk.common.modle;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 * @author jiangw
 * @date 2020/6/24 11:09
 * @since 1.0
 */
public class ApiResult<T> implements Serializable {

    /**
     * 返回状态
     */
    private int state;

    /**
     * 错误原因
     */
    private String cause;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回的变量
     */
    private Map<String, Object> variables;

    /**
     * 返回数据
     */
    private T data;

    public ApiResult() {
        this.state = HttpStatus.OK.value();
        this.cause = "";
        this.message = "";
        this.variables = new HashMap<String, Object>(16);
    }

    public ApiResult(int state) {
        this.state = state;
        this.message = "";
        this.cause = "";
        this.variables = new HashMap<String, Object>(16);
    }

    public ApiResult(int state, String message) {
        this.state = state;
        this.message = message;
        this.cause = "";
        this.variables = new HashMap<String, Object>(16);
    }

    public ApiResult(int state, String cause, String message) {
        this.state = state;
        this.cause = cause;
        this.message = message;
        this.variables = new HashMap<String, Object>(16);
    }

    public ApiResult(int state, String cause, String message, Map<String, Object> variables) {
        this.state = state;
        this.cause = cause;
        this.message = message;
        this.variables = variables;
    }

    public ApiResult(int state, String cause, String message, Map<String, Object> variables, T data) {
        this.state = state;
        this.cause = cause;
        this.message = message;
        this.variables = variables;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
