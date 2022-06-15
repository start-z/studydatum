package com.zhou.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zhouhelong
 * @creat 2022-06-10 16:58
 * @description:
 */
@Controller
@Slf4j
public class ViewController {

    @GetMapping({"/", "index"})
    @RequiresPermissions("user:1233")
    @RequiresRoles("1123123")
    public String toindex() {
        Subject currentUser = SecurityUtils.getSubject();
        log.info(currentUser.getPrincipal().toString());
        log.info("进入首页");
        return "html/index/index";
    }

    @GetMapping("tologin")
    public String toLogin() {
        log.info("111");
        return "html/login/login";
    }

@PostMapping("authLogin")
    public void authLogin(String username, String pwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        SecurityUtils.getSubject().login(token);
    }

}
