package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhouhelong
 * @creat 2022-06-10 17:02
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class othWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(othWorkApplication.class, args);
        System.out.println("other启动成功");
    }
}
