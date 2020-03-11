package com.ldh.rpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  在RPC中,发出请求的程序是客户端,而提供服务的程序是服务端
 */
public class AppClient {
    public static void main(String[] args) {
//        RpcProxyClient proxyClient = new RpcProxyClient();
//        //生成动态代理类
//        IHelloService helloService = proxyClient.clentProxy(IHelloService.class,"localhost","3333");
//        String result = helloService.sayHello(" sb!!!");
//        System.out.println(result);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient proxyClient = context.getBean(RpcProxyClient.class);
        IHelloService helloService = proxyClient.clentProxy(IHelloService.class, "v1.0");
        String result = helloService.sayHello(" sb!!!");
        System.out.println(result);
    }
}
