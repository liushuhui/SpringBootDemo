package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.pojo.Login;
import com.demo.springbootdemo.pojo.Register;
import com.demo.springbootdemo.pojo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        String token = loginService.login(login.getUsername(), login.getPassword(), login.getRole());
        if (token == null) {
            return ApiResponse.error(400, "用户名或密码错误");
        }
        if (token.equals("role")) {
            return  ApiResponse.error(400, "角色不匹配");
        }
        return ApiResponse.success(token);
    }

    @PostMapping("/register")
    @Operation(summary = "注册", description = "注册")
    public ApiResponse<String> register(@RequestBody Register register) {
        String user = loginService.register(register);
        if (user == null) {
            return ApiResponse.error(400, "用户名已存在");
        }
        return ApiResponse.success(user);
    }

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public ApiResponse<User> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Token");
        return ApiResponse.success(loginService.getUserInfo(token));
    }
}
