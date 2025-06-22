package com.zhou.config;

import com.zhou.entity.User;
import com.zhou.service.ThreadLocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class webConfig implements HandlerInterceptor {
    @Autowired
    private ThreadLocalUser threadLocalUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginIn = session.getAttribute("loginIn");
        if (loginIn != null && loginIn.equals("true")) {
            Object userInfo = session.getAttribute("userInfo");
            if (userInfo instanceof User) {
                threadLocalUser.setCurrentUser((User) userInfo);
            }
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            response.sendRedirect("https://www.baidu.com");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("处理后的线程用户名为" + threadLocalUser.getCurrentUser());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //防止内存泄露
        System.out.println("结束后的线程用户名为" + threadLocalUser.getCurrentUser());
        threadLocalUser.removeCurrentUser();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
