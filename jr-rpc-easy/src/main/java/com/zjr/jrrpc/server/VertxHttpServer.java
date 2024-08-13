package com.zjr.jrrpc.server;

import io.vertx.core.Vertx;

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
        httpServer.requestHandler(new HttpServerHandle());

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
