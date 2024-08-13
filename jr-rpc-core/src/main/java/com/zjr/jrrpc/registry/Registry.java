package com.zjr.jrrpc.registry;

import com.zjr.jrrpc.config.RegistryConfig;
import com.zjr.jrrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Registry {

    void init(RegistryConfig registryConfig);
    void register(ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException;
    void unRegister(ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException;
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);
    void destroy();
    void heartbeat();
    void watch(String serviceNodeKey);
}
