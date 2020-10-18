package com.bsk.common.util;

import com.bsk.common.id.DefaultIdGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * id工具类
 * @author jiangw
 * @date 2020/8/2 0:07
 * @since 1.0
 */
@Configuration
public class UniqueIdUtils {

    private static DefaultIdGenerator idGenerator;

    public UniqueIdUtils() {
        idGenerator = (DefaultIdGenerator) AppUtils.getBean(DefaultIdGenerator.class);
    }

    public static String getId() {
        return idGenerator.getId();
    }

    public static long getUid() {
        return idGenerator.getUid();
    }
}
