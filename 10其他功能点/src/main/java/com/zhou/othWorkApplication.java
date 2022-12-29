package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author zhouhelong
 * @creat 2022-06-10 17:02
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Order(11)
public class othWorkApplication   implements ApplicationContextInitializer {
    public static void main(String[] args) {
        SpringApplication.run(othWorkApplication.class, args);
        System.out.println("other启动成功");
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("我是刷新前注入bean的");
    }
}
