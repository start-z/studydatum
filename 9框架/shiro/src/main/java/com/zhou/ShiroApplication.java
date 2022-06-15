package com.zhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouhelong
 * @creat 2022-06-10 17:02
 * @description:
 */
@SpringBootApplication
@MapperScan("com.zhou.mapper")
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
        System.out.println("shiro启动成功");
    }
}
