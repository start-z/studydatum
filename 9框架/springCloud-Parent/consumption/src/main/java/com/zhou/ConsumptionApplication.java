package com.zhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhouhelong
 * @creat 2022-06-14 10:19
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumptionApplication.class, args);
    }
}
