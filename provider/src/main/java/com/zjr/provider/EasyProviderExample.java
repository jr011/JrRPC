package com.zjr.provider;

import com.zjr.common.service.UserService;
import com.zjr.jrrpc.registry.LocalRegistry;
import com.zjr.jrrpc.server.VertxHttpServer;

public class EasyProviderExample {
    public static void main(String[] args) {
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
