package com.ldh.rpc;

@RpcService(value = IHelloService.class, version = "v2.0")
public class HelloServiceImpl2 implements IHelloService {
    @Override
    public String sayHello(String content) {
        return "【v2.0】 say hello" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println(user);
        return "【v2.0】 SUCCESS";
    }
}
