package com.ldh.rpc.discovery;

public interface IServiceDiscovery {

    //根据服务名称返回服务地址
    String serviceAddress(String serviceName);
}
