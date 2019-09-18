package com.shareyuan.user.controller;

import com.shareyuan.annotation.Limit;
import com.shareyuan.common.Result;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("api/v1/test/")
@RestController
public class TestController {

    @GetMapping("test")
    @Limit(rate = 1, capacity =1)
    public Mono<String> checkToken(ServerHttpRequest request) {
        return Mono.just("test");
    }

}
