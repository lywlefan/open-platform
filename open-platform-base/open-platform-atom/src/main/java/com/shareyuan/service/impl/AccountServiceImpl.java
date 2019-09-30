package com.shareyuan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shareyuan.dto.AccountDto;
import com.shareyuan.entity.Account;
import com.shareyuan.mapper.AccountMapper;
import com.shareyuan.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service("accountService")
@AllArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {

    AccountMapper userMapper;

    @Override
    public Account save(AccountDto accountDto) {
        Account account = accountDto.convertToAccount();
        userMapper.insert(account);
        return account;
    }

    @Override
    public Account findOne(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public IPage<Account> selectPageVo(Page page) {
        return userMapper.selectPageVo(page);
    }
}
