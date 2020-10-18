package com.bsk.common.support;

import com.alibaba.fastjson.JSONObject;
import com.bsk.common.constant.StringPool;
import com.bsk.common.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 一个简单的json支持类，比如把Map或者某个实体类转换成json
 * @author jiangw
 * @date 2020/9/20 14:05
 * @since 1.0
 */
@Component
public class JsonSupport {

    public JSONObject entityToJson(Object target) throws Exception {
        if (BeanUtils.isEmpty(target)) {
            throw new Exception(target.getClass().getName() + " 目标对象为空");
        }
        JSONObject json = new JSONObject();
        Class<?> aClass = target.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String getMethodName = StringPool.GET + name.substring(0, 1).toUpperCase() + name.substring(1);
            Method method = aClass.getMethod(getMethodName);
            Object value = method.invoke(target, null);
            json.put(name, value);
        }
        return json;
    }

}
