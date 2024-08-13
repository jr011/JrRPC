package com.zjr.provider;

import com.zjr.common.service.UserService;
import com.zjr.common.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名为："+user.getName());
        return user;
    }
}
