package com.zhou.lister;

import com.zhou.lister.event.UserEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhouhelong
 * @creat 2022-07-27 17:52
 * @description:
 */
@Component
public class MyLister {

    @EventListener(classes = UserEvent.class)
    public void getEvent(UserEvent event) throws NoSuchFieldException {
        System.out.println("事件监听开始");
        System.out.println(event.getClass().getField("name"));
    }
}
