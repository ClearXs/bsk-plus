package com.bsk.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidateConfig {

    /**
     * Configure the Hibernate validator to throw an exception if the first mismatch occurs
     * @author jiangw
     * @date 2020/9/5 0:08
     */
    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).
                configure().
                addProperty("hibernate.validator.fail_fast", "true").
                buildValidatorFactory();
        return factory.getValidator();
    }
}
