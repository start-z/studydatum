package com.zhou.controller;

import com.zhou.lister.event.UserEvent;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhelong
 * @creat 2022-07-27 17:54
 * @description:
 */
@RestController
public class CommonController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
@GetMapping("/lister")
    public void listterEventTest() {
        User user = new User();
        user.setId("1");
        user.setName("zhou");
        applicationEventPublisher.publishEvent(new UserEvent(user));

    }
}
