package com.zjr.provider;


import com.zjr.common.service.UserService;
import com.zjr.jrrpc.registry.LocalRegistry;
import com.zjr.jrrpc.server.HttpServer;
import com.zjr.jrrpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 *
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
