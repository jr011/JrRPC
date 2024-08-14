package com.zjr.jrrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class MockServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据方法的返回值设置特定的对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}",method.getName());
        return getDefaultObject(methodReturnType);
    }

    private Object getDefaultObject(Class<?> type) {
        // 基本类型
        if(type.isPrimitive()){
            if(type == boolean.class){
                return false;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            } else if (type == byte.class) {
                return (byte) 0;
            } else if (type == float.class) {
                return 0.0f;
            } else if (type == double.class) {
                return 0.0d;
            }
        }
        return null;
    }
}
