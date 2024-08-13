package com.zjr.jrrpc.proxy;

import java.lang.reflect.Proxy;
/*
 * 服务代理工厂
 */
public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new ServiceProxy());
    }
}
