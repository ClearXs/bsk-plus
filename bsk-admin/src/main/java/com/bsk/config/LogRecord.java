package com.bsk.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 记录接口执行时间
 * @author jiangw
 * @date 2020/9/3 16:22
 * @since 1.0
 */
@Configuration
@Aspect
public class LogRecord {

    private final Logger logger = LoggerFactory.getLogger(LogRecord.class);

    /**
     *  匹配bsk下的所有包-controller下的在所有类-所有类型-所有方法
     * @author jiangw
     * @date 2020/9/3 17:10
     */
    @Pointcut("execution(* com.bsk..controller..*(..))")
    public void pointCut() {};


    @Before("pointCut()")
    public void before() {
    }

    @After("pointCut()")
    public void after() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object object;
        String method = point.getTarget().getClass().getName() + "." + point.getSignature().getName();
        long startTime = System.currentTimeMillis();
        object = point.proceed();
        long endTime = System.currentTimeMillis();
        logger.info(method + " 执行时间: " + (endTime - startTime));
        return object;
    }
}
