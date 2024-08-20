package com.zjr.jrrpc.server;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zjr.jrrpc.RpcApplication;
import com.zjr.jrrpc.model.RpcRequest;
import com.zjr.jrrpc.model.RpcResponse;
import com.zjr.jrrpc.model.ServiceMetaInfo;
import com.zjr.jrrpc.serializer.Serializer;
import com.zjr.jrrpc.serializer.SerializerFactory;

import java.io.IOException;

public class VertxHttpClient {
    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws IOException {
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            String serviceHost = serviceMetaInfo.getServiceHost();
            int servicePort = serviceMetaInfo.getServicePort();
            String url = "http://" + serviceHost + ":" + servicePort;
            try (HttpResponse httpResponse = HttpRequest.post(url)
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
