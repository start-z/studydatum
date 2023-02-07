package com.zhou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebFifter implements WebMvcConfigurer {
    @Autowired
     private  webConfig webConfig;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webConfig).addPathPatterns("/**").excludePathPatterns("/user/login");
    }

}
