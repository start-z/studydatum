package com.zhou.service;

import com.zhou.annotation.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        int i = 2 / 1;
    }
}
