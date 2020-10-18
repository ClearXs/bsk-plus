package com.bsk.common.support;

import java.lang.reflect.InvocationTargetException;

/**
 * 策略执行者
 * @author jiangw
 * @date 2020/9/4 10:14
 * @since 1.0
 */
public class ValidatorContext {

    private Validator validator;

    public ValidatorContext(Validator validator) {
        this.validator = validator;
    }

    public boolean execute() throws InvocationTargetException, IllegalAccessException {
        return validator.validate();
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
