package com.shareyuan.controller;

import com.google.gson.Gson;
import com.shareyuan.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping
@RestController
@AllArgsConstructor
public class UserController {

    UserMapper userMapper;

    @GetMapping("get")
    public Mono<String > get(){
        Gson gson = new Gson();
        return Mono.just(gson.toJson(userMapper.getCount()));
    }

}
