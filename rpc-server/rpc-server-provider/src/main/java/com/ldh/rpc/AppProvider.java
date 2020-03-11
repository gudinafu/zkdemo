package com.ldh.rpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppProvider {
    public static void main(String[] args) {
//        IHelloService instance = new HelloServiceImpl();
//        RpcProxyServer proxyServer = new RpcProxyServer();
//        proxyServer.publisher(instance,3333);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.start();
    }
}
