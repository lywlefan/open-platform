package com.shareyuan.utils;


import com.shareyuan.core.ReactiveRequestContextHolder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

/**
 * @Author : kent
 * @Description : 
 * @Date : 11:51 2019/9/17
 */
public class RequestHolder {

    public static Mono<ServerHttpRequest> getHttpServletRequest() {
        return  ReactiveRequestContextHolder.getRequest();
    }
}
