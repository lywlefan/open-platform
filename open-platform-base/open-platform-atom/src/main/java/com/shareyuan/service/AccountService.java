package com.shareyuan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareyuan.dto.AccountDto;
import com.shareyuan.entity.Account;

/**
 * @Author : kent
 * @Email : 330334064@qq.com
 * @Description : 用户service
 * @Date : 14:44 2019/9/30
 */
public interface AccountService{

    /**
     * 保存
     */
    Account save(AccountDto accountDto);

    /**
     * 根据id查找用户
     */
    Account findOne(String id);

    /**
     * 分页
     */
    IPage<Account> selectPageVo(Page page);


}
