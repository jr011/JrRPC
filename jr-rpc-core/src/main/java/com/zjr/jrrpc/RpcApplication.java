package com.zjr.jrrpc;

import com.zjr.jrrpc.config.RegistryConfig;
import com.zjr.jrrpc.config.RpcConfig;
import com.zjr.jrrpc.constant.RpcConstant;
import com.zjr.jrrpc.registry.Registry;
import com.zjr.jrrpc.registry.RegistryFactory;
import com.zjr.jrrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config={}",newRpcConfig.toString());
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        // 根据配置选择注册中心
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    public static void init(){
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e){
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }
    public static RpcConfig getRpcConfig() {
        if(rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if(rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
