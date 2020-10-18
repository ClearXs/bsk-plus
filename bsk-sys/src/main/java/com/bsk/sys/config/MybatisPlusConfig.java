package com.bsk.sys.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis插件配置，配置乐观锁拦截器/分页插件/逻辑删除，并且开启事务管理
 * @author jiangw
 * @date 2020/8/29 15:15
 * @since 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.bsk.*.mapper")
public class MybatisPlusConfig {

    /**
     * 乐观锁，表增加version，po类增加@Version
     * @author jiangw
     * @date 2020/9/2 10:09
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页
     * @author jiangw
     * @date 2020/9/2 10:10
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除，即在数据库表时增加deleted字段，po类增加@TableLogic
     * @author jiangw
     * @date 2020/9/2 10:07
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
