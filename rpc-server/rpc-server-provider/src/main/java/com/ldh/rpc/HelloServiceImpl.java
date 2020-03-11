package com.ldh.rpc;

@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        return "【v1.0】 say hello" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println(user);
        return "【v1.0】 SUCCESS";
    }
}
