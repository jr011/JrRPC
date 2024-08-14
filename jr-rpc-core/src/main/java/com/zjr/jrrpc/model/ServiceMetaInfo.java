package com.zjr.jrrpc.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class ServiceMetaInfo {
    private String serviceName;
    private String serviceVersion = "1.0";
    private String serviceHost;
    private Integer servicePort;
    private String serviceGroup = "default";


    /*
    获取服务键名
     */
    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /*
    获取服务注册节点键名
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s",getServiceKey(),serviceHost,servicePort);
    }
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}
