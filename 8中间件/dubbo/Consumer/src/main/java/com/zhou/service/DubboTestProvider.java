package com.zhou.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author zhouhelong
 * @creat 2022-07-27 15:48
 * @description:
 */
@Component
public class DubboTestProvider {
    @DubboReference
    private DubboTestService dubboTestService;
    public  String sayhello(){
        dubboTestService.sayHello();
        return   "hello";
    }
}
