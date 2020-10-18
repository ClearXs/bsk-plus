package com.bsk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TConfig {

    @Bean
    @ConditionalOnMissingBean
    public IBean bean() {
        return new TestBean();
    }
}
