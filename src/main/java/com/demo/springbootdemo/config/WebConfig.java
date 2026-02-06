package com.demo.springbootdemo.config;

import com.demo.springbootdemo.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")// 所有路径都需要 Token 校验
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**")
                .excludePathPatterns("/getDict")
        ;// 除了登录接口
    }
}
