package com.zhou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.mapper.UserMapper;
import com.zhou.entity.User;
import com.zhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 15:18:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements  UserService{
}
