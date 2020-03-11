package com.ldh.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable{

    private String className;

    private String MethodName;

    private Object[] args;

    private String version;
}
