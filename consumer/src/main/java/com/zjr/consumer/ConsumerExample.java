package com.zjr.consumer;

import com.zjr.common.model.User;
import com.zjr.common.service.UserService;
import com.zjr.jrrpc.config.RpcConfig;
import com.zjr.jrrpc.proxy.ServiceProxyFactory;
import com.zjr.jrrpc.utils.ConfigUtils;

import java.io.IOException;

public class ConsumerExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);
        UserService proxy = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("zjr");
        User newUser1 = proxy.getUser(user);
        User newUser2 = proxy.getUser(user);
        Thread.sleep(10*1000L);
        User newUser3 = proxy.getUser(user);
        if(newUser1 != null){
            System.out.println(newUser1.getName());
        } else{
            System.out.println("user == null");
        }
//        long number = proxy.getNumber();
//        System.out.println(number);

    }
}
