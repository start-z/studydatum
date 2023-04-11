package com.zhou.supermapforjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhou
 * @since 2023/4/11
 * description:
 */
@Configuration
public class SwaggerConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhou"))
                .build();
    }
}
