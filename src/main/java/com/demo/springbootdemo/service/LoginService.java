package com.demo.springbootdemo.service;

import com.demo.springbootdemo.mapper.LoginMapper;
import com.demo.springbootdemo.pojo.Login;
import com.demo.springbootdemo.util.IdGenerator;
import com.demo.springbootdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginService {

    @Autowired
    LoginMapper loginMapper;

    public String login(String username, String password) {
        Login originUser = loginMapper.getPasswordByUsername(username);

        if (originUser != null && originUser.getUsername().equals(username) && originUser.getPassword().equals(password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            return JwtUtil.generateToken(claims);
        }
        return null;
    }

    public String register(Login register) {
        Login  user = loginMapper.getPasswordByUsername(register.getUsername());
        if (user != null) {
            return null;
        }
        register.setUserId(IdGenerator.nextId());
        int affectedRows = loginMapper.register(register);
        return affectedRows > 0 ? "注册成功" : "注册失败";
    }
}
