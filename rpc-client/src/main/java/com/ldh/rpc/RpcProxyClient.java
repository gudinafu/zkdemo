package com.ldh.rpc;

import com.ldh.rpc.discovery.IServiceDiscovery;
import com.ldh.rpc.discovery.ServiceDiscovryWithZk;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    private IServiceDiscovery serviceDiscovery = new ServiceDiscovryWithZk();

    public <T> T clentProxy(final Class<T> interfaceCls,String version) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, new MyInvocationHandler(serviceDiscovery,version));

    }
}
