package com.zjr.jrrpc.config;

import com.zjr.jrrpc.serializer.SerializerKeys;
import lombok.Data;

@Data
public class RpcConfig {
    private String name = "jr-rpc";
    private String version = "1.0";
    private String serverHost = "localhost";
    private Integer serverPort = 8081;
    private boolean mock = false;
    private String serializer = SerializerKeys.JDK;
    private RegistryConfig registryConfig = new RegistryConfig();
}
