package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhouhelong
 * @creat 2022-06-08 10:23
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RibbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbitMqApplication.class, args);
    }
}
