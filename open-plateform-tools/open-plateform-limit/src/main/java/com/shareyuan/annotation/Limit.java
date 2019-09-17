package com.shareyuan.annotation;


import com.shareyuan.common.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : kent
 * @Description : 限流注解
 * @Date : 11:24 2019/9/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 资源名称，用于描述接口功能
     */
    String name() default "";

    /**
     * 资源 key
     */
    String key() default "";

    /**
     * key前缀
     */
    String prefix() default "";

    /**
     * 每秒可以取到的令牌数
     */
    int rate();

    /**
     * 令牌桶容量
     */
    int capacity();

    /**
     * 限制类型
     */
    LimitType limitType() default LimitType.CUSTOMER;

}