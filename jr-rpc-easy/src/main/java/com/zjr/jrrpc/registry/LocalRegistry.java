package com.zjr.jrrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegistry {
    private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();

    public static void register(String name, Class<?> clazz) {
        System.out.println("注册类名称：" + name + "注册类类型：" + clazz.getName());
        map.put(name, clazz);
    }

    public static Class<?> get(String name) {
        return map.get(name);
    }

    public static void remove(String name) {
        map.remove(name);
    }
}
