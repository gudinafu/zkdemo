package com.ldh.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public RpcProxyClient proxyClient(){
       return new RpcProxyClient();
    }
}
