package com.zhou.controller;

import com.zhou.entity.User;
import com.zhou.event.UserEvent;
import com.zhou.service.ThreadLocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 文档测试控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ThreadLocalUser threadLocalUser;
    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 线程监听
     * @return  返回当前用户线程监听
     */
    @GetMapping("/listener")
    public String listener() {
        System.out.println("controller用户名为" + threadLocalUser.getCurrentUser());
        applicationEventPublisher.publishEvent(new UserEvent("hello"));
        String name = Thread.currentThread().getName();
        return name + "当前线程的变量值为" + threadLocalUser.local.get() + ";controller";
    }

    /**
     * 用户登录
     * @param request  请求体
     * @return   系统登录成功
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        User user = new User("ceshi123", "hello", 20, "123", "https", "123111");
        applicationEventPublisher.publishEvent(new UserEvent(user));
        HttpSession session = request.getSession();
        session.setAttribute("loginIn", "true");
        session.setAttribute("userInfo", user);
        return "系统登陆成功";
    }

    /**
     *  自定义注解校验
     * @param user  测试类
     * @return  string
     */
    @GetMapping("/check")
    public String login(@Valid User  user) {

        return user.toString();
    }
}
