package com.zhou;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class
        , org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class ActiviApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(ActiviApplicaiton.class, args);
    }

}

