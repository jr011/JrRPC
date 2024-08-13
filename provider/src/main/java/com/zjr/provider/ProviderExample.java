package com.zjr.provider;

import com.zjr.common.service.UserService;
import com.zjr.jrrpc.RpcApplication;
import com.zjr.jrrpc.config.RegistryConfig;
import com.zjr.jrrpc.config.RpcConfig;
import com.zjr.jrrpc.model.ServiceMetaInfo;
import com.zjr.jrrpc.registry.LocalRegistry;
import com.zjr.jrrpc.registry.Registry;
import com.zjr.jrrpc.registry.RegistryFactory;
import com.zjr.jrrpc.server.tcp.VertxTcpServer;

public class ProviderExample {
    public static void main(String[] args) {
        RpcApplication.init();

        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName,UserServiceImpl.class);

        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try{
            registry.register(serviceMetaInfo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
