package com.zjr.common.service;

import com.zjr.common.model.User;

import java.io.IOException;

public interface UserService {
    User getUser(User user) throws IOException;
    // 测试
    default short getNumber(){
        return (short) 1;
    }
}
