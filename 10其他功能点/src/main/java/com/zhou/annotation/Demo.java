package com.zhou.annotation;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

/**
 * @author zhouhelong
 * @creat 2022-07-13 19:39
 * @description:
 */
@Target(ElementType.METHOD)  //表名在什么字段上使用
@Retention(RetentionPolicy.RUNTIME)  //表明注解在多久停止
@Documented  //注解生成文档
@Inherited  //表名注解可继承性
public @interface Demo {
    String message() default "hello";

}
