package com.zhou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhouhelong
 * @creat 2022-06-08 10:37
 * @description:
 */
@Configuration
@EnableOpenApi
public class Swagger2Config {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo());
    }
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "redis",
                "redisTest",
                "v1.0",
                "localhost:9000",
                "redis tEST",
                "demo",
                "demo1"
        );

    }
}
