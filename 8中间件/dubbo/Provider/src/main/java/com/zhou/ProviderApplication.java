package com.zhou;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhouhelong
 * @creat 2022-07-27 11:39
 * @description: 启动类
 */
@EnableDubbo(scanBasePackages = {"com.zhou"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
