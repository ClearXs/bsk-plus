package com.bsk.common.support;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>一个验证器，对于数据的校验。</p>
 * <p>1.比如数据是否存在于数据库中，</p>
 * <p>2.数据格式是否正确.</p>
 * <p>3.验证码的实现.</p>
 * @author jiangw
 * @date 2020/9/4 9:59
 * @since 1.0
 */
public interface Validator {

    /**
     * 返回true为有数据，false为没有数据
     * @author jiangw
     * @date 2020/9/4 10:34
     */
    boolean validate() throws InvocationTargetException, IllegalAccessException;
}
