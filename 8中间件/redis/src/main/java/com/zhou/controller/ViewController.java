package com.zhou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhelong
 * @since 2022-06-08 14:22
 * @description:
 */
@RequestMapping("inisView")
@Slf4j
@Controller
public class ViewController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("swagger")
    public String toSwagger() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "进入了swagger页面");
        stringRedisTemplate.opsForValue().set("timeout", "100", 20, TimeUnit.SECONDS);
        return "swagger-ui/index.html";
    }

}
