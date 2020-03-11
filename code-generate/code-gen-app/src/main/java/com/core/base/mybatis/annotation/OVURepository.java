package com.core.base.mybatis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface OVURepository {
    String value() default "";
}