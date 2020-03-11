package com.ldh.rpc;


import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.TYPE)//修饰的是类或者接口
@Retention(RetentionPolicy.RUNTIME) //运行时状态
@Component //被spring解析
public @interface RpcService {

    Class<?> value();  //拿到服务的接口
    String version() default "";
}
