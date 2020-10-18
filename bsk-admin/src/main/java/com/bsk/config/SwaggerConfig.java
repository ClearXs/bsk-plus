package com.bsk.config;

import com.bsk.security.shiro.constant.TokenConstant;
import io.swagger.annotations.Api;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    private boolean active;

    @Bean
    public Docket adminDocket() {
        // 给所有请求加上token请求
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
        tokenParameterBuilder.name(TokenConstant.ACCESS_TOKEN).
                description("令牌").
                modelRef(new ModelRef("string")).
                parameterType("header").
                required(false).
                build();
        parameters.add(tokenParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                groupName("admin").
                enable(active).select().
                apis(RequestHandlerSelectors.basePackage("com.bsk.controller")).
                build().
                globalOperationParameters(parameters);
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                groupName("api").
                enable(active).select().
                apis(RequestHandlerSelectors.basePackage("com.bsk.security.controller")).
                build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfo("bsk接口文档", "", "1.0", "urn:tos",
                new Contact("", "", ""), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
