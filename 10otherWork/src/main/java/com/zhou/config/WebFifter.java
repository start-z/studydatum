package com.zhou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebFifter implements WebMvcConfigurer {
    @Resource
    private webConfig webConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(webConfig).addPathPatterns("/**").excludePathPatterns(
                "/user/login",
                "/user/check"
        );
    }

}
