package com.zjr.consumer;

import com.zjr.common.model.User;
import com.zjr.common.service.UserService;
import com.zjr.jrrpc.proxy.ServiceProxy;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class EasyConsumerExample {
    public static void main(String[] args) throws IOException {
//        UserService userService = new UserServiceProxy(); // 静态代理
//        UserService userService = ServiceProxyFactory.getProxy(UserService.class);//动态代理
        UserService userService = (UserService) Proxy.newProxyInstance(
                                                    UserService.class.getClassLoader(),
                                                    new Class[]{UserService.class},
                                                    new ServiceProxy());
        User user = new User();
        user.setName("zjr");

        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }
        else{
            System.out.println("user == null");
        }
    }
}
