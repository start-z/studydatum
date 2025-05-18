package com.zhou.service;

import com.zhou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUser {
    public static ThreadLocal<User> local = new ThreadLocal<>();
    public User getCurrentUser() {
        return local.get();
    }

    public void setCurrentUser(User user) {
        local.set(user);
    }

    public void removeCurrentUser() {
        local.remove();
    }
}
