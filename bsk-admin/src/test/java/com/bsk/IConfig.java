package com.bsk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IConfig {

    @Bean
    public IBean bean() {
        return new OtherBean();
    }
}
