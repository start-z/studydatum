package com.zhou;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class
        , org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class ActiviApplicaiton   {
    public static void main(String[] args) {
        SpringApplication.run(ActiviApplicaiton.class, args);
    }
    //刷新上下文启动成功
    @PostConstruct
    public void  springAfterRunner(){
        System.out.println("系统刷新启动");
    }
}

