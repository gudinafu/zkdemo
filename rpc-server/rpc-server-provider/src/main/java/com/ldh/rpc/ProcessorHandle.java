package com.ldh.rpc;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ProcessorHandle implements Runnable {

    private Socket socket;
    private Map<String, Object> handleMap;

    public ProcessorHandle(Map<String, Object> handleMap, Socket socket) {
        this.socket = socket;
        this.handleMap = handleMap;
    }


    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //获取输入流中包含的信息
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //反射调用本地服务
            Object result = invoke(rpcRequest);
            //结果写出
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) {

        Object bean = null;
        String version = rpcRequest.getVersion();
        if (!StringUtils.isEmpty(version)) {
            bean = handleMap.get(rpcRequest.getClassName() + "-" + version);
        }

        if (null == bean) {
            throw new RuntimeException("Not Found Bean :" + rpcRequest.getClassName());
        }

        Object[] args = rpcRequest.getArgs();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        Object object = null;
        try {
            Class<?> clazz = Class.forName(rpcRequest.getClassName());
            Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
            object = method.invoke(bean, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
