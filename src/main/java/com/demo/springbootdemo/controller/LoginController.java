package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.pojo.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.demo.springbootdemo.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "登录管理", description = "登录管理相关接口")
@RestController

public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "登录")
    public ApiResponse<String> login(@RequestBody Login login) {
        String token = loginService.login(login.getUsername(), login.getPassword());
        if (token == null) {
            return ApiResponse.error(400, "用户名或密码错误");
        }
        return ApiResponse.success(token);
    }

    @PostMapping("/register")
    @Operation(summary = "注册", description = "注册")
    public ApiResponse<String> register(@RequestBody Login register) {
        String user = loginService.register(register);
        if (user == null) {
            return  ApiResponse.error(400, "用户名已存在");
        }
        return ApiResponse.success(user);
    }
}
