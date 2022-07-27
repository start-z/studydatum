package com.zhou.controller;

import com.zhou.service.DubboTestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhelong
 * @creat 2022-07-27 17:23
 * @description:
 */
@RestController
public class ConsumerController {

    @Autowired
    private DubboTestProvider dubboTestProvider;

    @GetMapping("/hello")
    public String hello() {
        return dubboTestProvider.sayhello();
    }
}
