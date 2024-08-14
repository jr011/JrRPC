package com.zjr.consumer;


import com.zjr.common.model.User;
import com.zjr.common.service.UserService;
import com.zjr.jrrpc.bootstrap.ConsumerBootstrap;
import com.zjr.jrrpc.proxy.ServiceProxyFactory;

import java.io.IOException;

/**
 * 服务消费者示例
 *
 */
public class ConsumerExample {

    public static void main(String[] args) throws IOException {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("zjr");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
