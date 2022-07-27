package com.zhou.service.impl;

import com.zhou.service.DubboTestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author zhouhelong
 * @creat 2022-07-27 15:23
 * @description:
 */
@DubboService
@Service
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public void sayHello() {
        System.out.println("111");
    }
}
