package com.zjr.jrrpc.server.tcp;

//import com.zjr.jrrpc.server.HttpServer;
//import com.zjr.jrrpc.server.VertxHttpServer;

import com.zjr.jrrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class VertxTcpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        // 创建vertx实例
        Vertx vertx = Vertx.vertx();
        //创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        //处理请求
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String testMessage = "Hello, server!Hello, Server!Hello, Server!Hello, Server!";
                int testLength = testMessage.getBytes().length;
                byte[] requestData = buffer.getBytes();
                if(requestData.length<testLength) {
                    System.out.println("半包，length = " + requestData.length);
                    return;
                }
                else if(requestData.length>testLength) {
                    System.out.println("粘包，length = " + requestData.length);
                    return;
                }
                System.out.println("Request from client: " + new String(requestData));
                byte[] responseData = handleRequest(requestData);
                socket.write(Buffer.buffer(responseData));
            });
        });
        server.listen(port,result->{
            if(result.succeeded()){
                System.out.println("Server started on port "+port);
            } else{
                System.err.println("Server failed to start on port "+port);
            }
        });
    }

    private byte[] handleRequest(byte[] requestData) {
        return "Hello, Client".getBytes();
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
