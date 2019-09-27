package com.shareyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareyuan.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    int getCount();

}