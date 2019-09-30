package com.shareyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName("open_account")
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Account {

    private Long id;

    private String name;

    private Integer age;
    
}