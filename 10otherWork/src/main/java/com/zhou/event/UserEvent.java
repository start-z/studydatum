package com.zhou.event;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    public UserEvent(Object source) {
        super(source);
    }
}
