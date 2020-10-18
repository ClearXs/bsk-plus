package com.bsk.security.shiro.config;

import com.bsk.security.shiro.filter.AccessFilter;
import com.bsk.security.shiro.filter.BskLogoutFilter;
import com.bsk.security.shiro.listener.ShiroSessionListener;
import com.bsk.security.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.port}")
    private int port;

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(name = "defaultSecurityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置实体（域）
        securityManager.setRealm(userRealm);
        // 配置session管理
        securityManager.setSessionManager(sessionManager());

        // 配置缓存管理
        securityManager.setCacheManager(cacheManager());

        // 配置cookie管理
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 过滤链拦截请求，
        // 比如说:/sys/user/createUser = perms['user:add']。创建新的角色需要拥有user:add的权限
        // /sys/security/login = anon 即登录不需要进行认证
        Map<String, Filter> filters = new HashMap<>();
        filters.put("accessFilter", new AccessFilter());
        filters.put("logoutFilter", new BskLogoutFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/sys/user/*", "accessFilter");
        filterChainDefinitionMap.put("/sys/user/createUser", "perms[user:add]");
        filterChainDefinitionMap.put("/api/security/login", "anon");

        filterChainDefinitionMap.put("/api/security/logout", "logoutFilter");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 静态注入，相当于调用SecurityUtils.setSecurityManager(securityManager) 如果不进行调用，额shiroFilter与securityManager会循环调用
     * @author jiangw
     * @date 2020/8/28 14:42
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager){
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager);
        return bean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        // 使用redis缓存作为持久化方案
        sessionManager.setSessionDAO(sessionDAO());
        // 监听会话
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        // 设置session超时时间
        sessionManager.setGlobalSessionTimeout(300000);
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    private RedisCacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }



    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        redisManager.setDatabase(database);
        redisManager.setTimeout(timeout);
        return redisManager;
    }
}
