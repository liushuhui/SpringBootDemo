package com.demo.springbootdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    //文档基础信息配置
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("学生管理")
                        .description("学生信息的接口文档")
                        .version("v0.0.1")
                        .contact(new Contact().name("开发者").email("zhangsan@163.com"))
                );
    }
}
