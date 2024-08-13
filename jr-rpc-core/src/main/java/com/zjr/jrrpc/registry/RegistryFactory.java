package com.zjr.jrrpc.registry;

import com.zjr.jrrpc.spi.SpiLoader;

public class RegistryFactory {
    static {
        SpiLoader.load(Registry.class);
    }
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class,key);
    }
}
