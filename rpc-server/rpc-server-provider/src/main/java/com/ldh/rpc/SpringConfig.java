package com.ldh.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ldh.rpc")
public class SpringConfig {

    @Bean
    public RpcProxyServer getRpcServer(){
        return new RpcProxyServer(6666);
    }
}
