package com.bsk.common.util;

import com.bsk.common.modle.ApiResult;
import com.bsk.common.support.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一些http相关的操作
 * @author jiangw
 * @date 2020/9/30 22:41
 * @since 1.0
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 一个通用的返回结果
     * @param response res
     * @param status http状态码
     * @param apiResult 通用返回结果
     */
    public static void resReturn(HttpServletResponse response, int status, ApiResult<String> apiResult) {
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            JsonSupport jsonSupport = AppUtils.getBean(JsonSupport.class);
            out.append(jsonSupport.entityToJson(apiResult).toJSONString());
        } catch (IOException e) {
            logger.error("返回Response信息出现IOException异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void resOk(HttpServletResponse response, String message) {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setMessage(message);
        apiResult.setState(HttpStatus.OK.value());
        resReturn(response, HttpStatus.OK.value(), apiResult);;
    }

    public static void resError(HttpServletResponse response, String message) {
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setMessage(message);
        apiResult.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resReturn(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), apiResult);
    }
}
