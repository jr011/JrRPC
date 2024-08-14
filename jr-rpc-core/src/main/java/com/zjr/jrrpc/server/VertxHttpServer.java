package com.zjr.jrrpc.server;

import io.vertx.core.Vertx;

/*
监听端口，使用HttpServerHandler处理请求。
 */
public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        /*
        httpServer.requestHandler(request->{
            System.out.println("Received request:" + request.method() + " " + request.uri());

            request.response().putHeader("content-type", "text/plain").end("Hello from vert.x HTTP server!");
        });
        */
        //处理请求
        httpServer.requestHandler(new HttpServerHandler());

        // 启动http服务器并监听指定端口
        httpServer.listen(port,result->{
            if(result.succeeded()){
                System.out.println("Server is now listening or port " + port);
            }
            else{
                System.out.println("Failed to start server" + result.cause());
            }
        });
    }
}
