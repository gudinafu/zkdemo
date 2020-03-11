package com.ldh.rpc;

import com.ldh.rpc.discovery.IServiceDiscovery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
@NoArgsConstructor
public class MyInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;
    private String version;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        rpcRequest.setVersion(version);

        String serviceName = rpcRequest.getClassName() + "-" + rpcRequest.getVersion();
        String serviceAddress = serviceDiscovery.serviceAddress(serviceName);

        if(StringUtils.isEmpty(serviceAddress)){
            return null;
        }
        RpcNetTransport transport = new RpcNetTransport(serviceAddress);

        return transport.send(rpcRequest);
    }
}
