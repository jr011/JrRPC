package com.zjr.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zjr.common.model.User;
import com.zjr.common.service.UserService;
import com.zjr.jrrpc.model.RpcRequest;
import com.zjr.jrrpc.model.RpcResponse;
import com.zjr.jrrpc.serializer.JdkSerializer;
import com.zjr.jrrpc.serializer.Serializer;

import java.io.IOException;

public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) throws IOException {
        Serializer serializer = new JdkSerializer();
        RpcRequest request = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .parameters(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(request);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bodyBytes).execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
