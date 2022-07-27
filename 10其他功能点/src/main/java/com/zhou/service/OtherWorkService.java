package com.zhou.service;

import com.zhou.annotation.Demo;
import com.zhou.aspect.DemoAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * @author zhouhelong
 * @creat 2022-07-13 20:33
 * @description:
 */
@Service
@Slf4j
public class OtherWorkService {
    @Demo(message = "123")
    public void hello() {
        log.info("我进入service层");
        int i = 2 / 0;
    }
}
