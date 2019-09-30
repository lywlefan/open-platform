package com.shareyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareyuan.dto.AccountDto;
import com.shareyuan.entity.Account;
import com.shareyuan.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Author : kent
 * @Email : 330334064@qq.com
 * @Description : 会员
 * @Date : 16:50 2019/9/30
 */
@RequestMapping
@RestController
@AllArgsConstructor
public class AccountController {

    AccountService accountService;

    @GetMapping("list")
    public Mono<IPage<Account>> getPage(){
       Page page = new Page();
       return Mono.just(accountService.selectPageVo(page));
    }

    @GetMapping("save")
    public Mono<Account> sava(@RequestBody AccountDto accountDto){
        return Mono.just(accountService.save(accountDto));
    }

    @GetMapping("get/{id}")
    public Mono<Account> get(@PathVariable String id){
        return Mono.just(accountService.findOne(id));
    }
}
