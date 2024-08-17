package com.zjr.common.service;

import com.zjr.common.model.User;

import java.io.IOException;

public class UserServiceMock implements UserService {
    @Override
    public User getUser(User user) throws IOException {
        User u = new User();
        u.setName("mock");
        return u;
    }
}
