package com.zjr.jrrpc.registry;

import com.zjr.jrrpc.model.ServiceMetaInfo;

import java.util.List;

public class RegistryServiceCache {
    List<ServiceMetaInfo> serviceCache;
    void writeCache(List<ServiceMetaInfo> serviceCache) {
        this.serviceCache = serviceCache;
    }
    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }
    void clearCache() {
        this.serviceCache = null;
    }

}
