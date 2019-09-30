package com.shareyuan.dto;

import com.google.common.base.Converter;
import com.shareyuan.entity.Account;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

@Data
public class AccountDto {

    private String name;

    private Integer age;

    public Account convertToAccount(){
        AccountDtoConvert accountDtoConvert = AccountDtoConvert.of();
        Account convert = accountDtoConvert.convert(this);
        return convert;
    }

    public AccountDto convertFor(Account account){
        AccountDtoConvert userDTOConvert = new AccountDtoConvert();
        AccountDto convert = userDTOConvert.reverse().convert(account);
        return convert;
    }

    @Accessors(chain = true)
    @RequiredArgsConstructor(staticName = "of")
    private static class AccountDtoConvert extends Converter<AccountDto, Account> {
        @Override
        protected Account doForward(AccountDto userDTO) {
            Account account = Account.of();
            BeanUtils.copyProperties(userDTO,account);
            return account;
        }

        @Override
        protected AccountDto doBackward(Account user) {
            AccountDto userDTO = new AccountDto();
            BeanUtils.copyProperties(user,userDTO);
            return userDTO;
        }
    }

}
