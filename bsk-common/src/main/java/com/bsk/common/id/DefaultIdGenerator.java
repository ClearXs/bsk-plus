package com.bsk.common.id;

import com.bsk.common.util.AppUtils;
import com.bsk.common.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 使用snowflakeld算法生成id
 * @author jiangw
 * @date 2020/8/2 0:04
 * @since 1.0
 */
@Component
public class DefaultIdGenerator implements IdGenerator, Serializable {

    private static final long serialVersionUID = 3405824291434808429L;

    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public String getId() {
        return String.valueOf(getUid());
    }

    @Override
    public long getUid() {
        if (BeanUtils.isEmpty(snowflakeIdWorker)) {
            init();
        }
        return snowflakeIdWorker.nextId();
    }

    private void init() {
        Long workerId = AppUtils.getProperty("bsk.uniqueId.workerId", Long.class);
        Long dataCenterId = AppUtils.getProperty("bsk.uniqueId.dataCenterId", Long.class);
        snowflakeIdWorker = new SnowflakeIdWorker(workerId, dataCenterId);
    }
}
