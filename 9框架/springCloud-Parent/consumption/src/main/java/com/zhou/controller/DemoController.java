package com.zhou.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.PostVMInitHook;

@RestController
@RequestMapping("consumption")
public class DemoController {
@GetMapping("test")
    public Object test(){
        return "我是consumption模块";
    }
}
