package com.zjr.jrrpc.server;

import com.zjr.jrrpc.model.RpcRequest;
import com.zjr.jrrpc.model.RpcResponse;
import com.zjr.jrrpc.registry.LocalRegistry;
import com.zjr.jrrpc.serializer.JdkSerializer;
import com.zjr.jrrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandle implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        final Serializer serializer = new JdkSerializer();

        System.out.println("Received request: " + request.method() + " " + request.uri());

        // 异步处理Http请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try{
                rpcRequest = serializer.deserialize(bytes,RpcRequest.class);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            if(rpcRequest == null){
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request,rpcResponse,serializer);
            }
            try{
                //获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(),rpcRequest.getParameters());
                rpcResponse.setData(result);
                rpcResponse.setMessage("ok");
                rpcResponse.setDataType(method.getReturnType());
            }
            catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request,rpcResponse,serializer);
        });
    }
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse response = request.response().putHeader("content-type","application/json");
        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            response.end(Buffer.buffer(serialized));
            } catch (IOException e) {
                e.printStackTrace();
                response.end(Buffer.buffer());
            }
    }
}
