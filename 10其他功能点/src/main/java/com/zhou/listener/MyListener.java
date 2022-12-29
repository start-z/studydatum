package com.zhou.listener;

import com.zhou.event.UserEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class MyListener {

    @EventListener(classes = com.zhou.event.UserEvent.class)
    public void getEvent(UserEvent event) throws NoSuchFieldException {
        System.out.println("事件监听开始");
        System.out.println(event.getSource().getClass().getField("name"));
    }

//    @EventListener(classes = ApplicationEvent.class)
    public void getListener(ApplicationEvent event) {
        //判断事件为MyEvent时候执行
//        if(event instanceof MyEvent){
//            //强制转换
//            MyEvent evt=(MyEvent) event;
//            //执行自定义事件中的自定义方法
//            evt.myevent();
//        }
        if (event instanceof ServletRequestHandledEvent) {
            ServletRequestHandledEvent servletRequestHandledEvent = (ServletRequestHandledEvent) event;
            Object source = servletRequestHandledEvent.getSource();
            //可以使用服务监听来打印请求日志
//            System.out.println("服务器请求了");
        }
        //如果容器关闭时，触发
        if (event instanceof ContextClosedEvent) {
            ContextClosedEvent cce = (ContextClosedEvent) event;
            System.out.println("#####################");
            System.out.println("容器关闭");
            System.out.println(cce);
            System.out.println("#####################");
        }
        //容器刷新时候触发
        if (event instanceof ContextRefreshedEvent) {
            ContextRefreshedEvent cre = (ContextRefreshedEvent) event;
            System.out.println("#####################");
            System.out.println("容器刷新");
            System.out.println(cre);
            System.out.println("#####################");
        }
        //容器启动的时候触发
        if (event instanceof ContextStartedEvent) {
            ContextStartedEvent cse = (ContextStartedEvent) event;
            System.out.println("#####################");
            System.out.println("容器启动");
            System.out.println(cse);
            System.out.println("#####################");
        }
        //容器停止时候触发
        if (event instanceof ContextStoppedEvent) {
            ContextStoppedEvent cse = (ContextStoppedEvent) event;
            System.out.println("#####################");
            System.out.println("容器停止");
            System.out.println(cse);
            System.out.println("#####################");
        }
    }
}
