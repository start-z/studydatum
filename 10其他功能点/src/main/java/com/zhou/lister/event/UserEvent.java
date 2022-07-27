package com.zhou.lister.event;


import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author zhouhelong
 * @creat 2022-07-27 17:50
 * @description:
 */
public class UserEvent extends ApplicationEvent {

    public UserEvent(Object source) {
        super(source);
    }
}
