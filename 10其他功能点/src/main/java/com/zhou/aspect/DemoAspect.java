package com.zhou.aspect;

import com.zhou.annotation.Demo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author zhouhelong
 * @creat 2022-07-13 19:58
 * @description:
 */
@Aspect
@Component
@Slf4j
public class DemoAspect {
    @Pointcut("@annotation(com.zhou.annotation.Demo)")
    public void logout() {
        log.info("我进入了切面");
    }

    @AfterReturning(pointcut = "@annotation(demo)")
    public void  afterRun(JoinPoint joinPoint, Demo  demo){
        log.info("我是运行中");
    }
    @AfterThrowing(pointcut = "@annotation(demo)")
    public void  afterThrow(JoinPoint joinPoint, Demo  demo){
        log.info("我是运行异常");
    }
}
