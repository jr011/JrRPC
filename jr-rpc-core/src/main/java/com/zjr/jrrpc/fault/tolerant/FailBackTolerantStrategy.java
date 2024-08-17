package com.zjr.jrrpc.fault.tolerant;

import com.zjr.jrrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 降级到其他服务 - 容错策略
 *
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String methodName = context.get("methodName").toString();
        String className = context.get("className").toString();
        Object[] parameters = (Object[]) context.get("parameters");
        Class<?>[] parameterTypes = (Class<?>[]) context.get("parameterTypes");
        String newClassName = className + "Mock";
        Class<?> aClass = Class.forName(newClassName);
        Method method = aClass.getMethod(methodName, parameterTypes);
        Object aInstance = aClass.getDeclaredConstructor().newInstance();
        Object result = method.invoke(aInstance, parameters);
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setData(result);
        return rpcResponse;
    }
}
