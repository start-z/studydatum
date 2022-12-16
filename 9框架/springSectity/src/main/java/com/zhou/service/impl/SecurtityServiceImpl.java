package com.zhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecurtityServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.zhou.entity.User one = userService.getOne(new QueryWrapper<com.zhou.entity.User>().eq("username", username));
        if (null != one) {
            return new User(one.getUsername(), one.getPassword(), Collections.EMPTY_LIST);
        }
        return null;
    }
}
