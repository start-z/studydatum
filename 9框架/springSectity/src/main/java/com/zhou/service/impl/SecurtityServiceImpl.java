package com.zhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.acl.PrincipalImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurtityServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("我是测试方法");
        String encode = bCryptPasswordEncoder.encode("123");
        bCryptPasswordEncoder.upgradeEncoding("123");
        com.zhou.entity.User one = userService.getOne(new QueryWrapper<com.zhou.entity.User>().eq("username", username));
        if (null != one) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new JaasGrantedAuthority("USER", new PrincipalImpl(one.getUsername())));
            return new User(one.getUsername(), one.getPassword(), authorities);
        }
        return null;
    }
}
