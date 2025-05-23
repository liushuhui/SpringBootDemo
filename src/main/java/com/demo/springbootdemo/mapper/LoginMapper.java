package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
public interface LoginMapper {
    Login getPasswordByUsername(String username);

    int register(Login register);
}
