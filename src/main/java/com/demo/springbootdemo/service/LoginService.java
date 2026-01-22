package com.demo.springbootdemo.service;

import com.demo.springbootdemo.mapper.LoginMapper;
import com.demo.springbootdemo.pojo.Login;
import com.demo.springbootdemo.pojo.Register;
import com.demo.springbootdemo.pojo.User;
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

    public String login(String username, String password, String role) {
        Login originUser = loginMapper.getPasswordByUsername(username);
        if (!originUser.getRole().equals(role)) {
            return "role";
        }

        if (originUser.getUsername().equals(username) && originUser.getPassword().equals(password)) {
            Map<String, Object> claims = Map.of("username", username, "userId", originUser.getUserId(), "role", role);
            return JwtUtil.generateToken(claims);
        }
        return null;
    }

    public String register(Register register) {
        Login user = loginMapper.getPasswordByUsername(register.getUsername());
        if (user != null) {
            return null;
        }
        register.setUserId(IdGenerator.nextId());
        int affectedRows = loginMapper.register(register);
        return affectedRows > 0 ? "注册成功" : "注册失败";
    }

    public User getUserInfo(String token) {
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Map<String, Object> UserData = (Map<String, Object>) claims.get("users");
        log.info("claims: {}, {}", claims, new User(UserData));
        return new User(UserData);
    }
}
