package com.ldh.rpc;

import com.ldh.rpc.registry.RegistryCentre;
import com.ldh.rpc.registry.RegistryCentreWithZK;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class RpcProxyServer implements ApplicationContextAware, InitializingBean {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private int port;

    private Map<String, Object> handleMap = new HashMap<>();

    private RegistryCentre registryCentre = new RegistryCentreWithZK();

    public RpcProxyServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //ServerSocket是等待客户端的请求，一旦获得一个连接请求，就创建一个Socket示例来与客户端进行通信。
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();//阻塞BIO
                //每一个socket交给一个ProcessorHandle来处理
                executorService.execute(new ProcessorHandle(handleMap, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!beanMap.isEmpty()) {
            for (Object object : beanMap.values()) {
                RpcService rpcService = object.getClass().getAnnotation(RpcService.class);
                String serverName = rpcService.value().getName();
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    serverName += "-" + version;
                }
                handleMap.put(serverName, object);
                registryCentre.registry(serverName, getAddress() + ":" + port);
            }
        }
    }

    private static String getAddress() {
        InetAddress inetAddress = null;
        try {
            inetAddress = inetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostAddress();//获得本机的ip地址
    }
}
