package com.zjr.jrrpc.proxy;

import java.lang.reflect.Proxy;
/*
 * 服务代理工厂
 */
public class ServiceProxyFactory {
    /*
    根据服务类获取代理对象
     */
    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new ServiceProxy());
    }
    /*
    根据服务类获取Mock代理对象
     */
    public static <T> T getMockProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MockServiceProxy()
        );
    }
}
