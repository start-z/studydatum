package com.zhou.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

public class InitSpring implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Iterator<String> beanNamesIterator = applicationContext.getBeanFactory().getBeanNamesIterator();
       while (beanNamesIterator.hasNext()){
           String next = beanNamesIterator.next();
           System.out.println("我的名字是"+next);
       }
        System.out.println("我是上下文");
    }
}
