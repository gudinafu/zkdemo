package com.ldh.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Data
@AllArgsConstructor
public class RpcNetTransport {

    //服务地址
    private String serviceAddress;

    public Object send(RpcRequest rpcRequest) {
        Socket socket;
        Object result = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            String[] url = serviceAddress.split(":");
            socket = new Socket(url[0], Integer.valueOf(url[1]));//建立连接
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//网络socket
            objectOutputStream.writeObject(rpcRequest);//序列化
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();

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
        return result;
    }

}
