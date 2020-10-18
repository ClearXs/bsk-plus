package com.bsk.security.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 账号删除异常
 * @author jiangw
 * @date 2020/9/7 18:27
 * @since 1.0
 */
public class DeleteAccountException extends AccountException {

    public DeleteAccountException() {
    }

    public DeleteAccountException(String message) {
        super(message);
    }

    public DeleteAccountException(Throwable cause) {
        super(cause);
    }

    public DeleteAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
