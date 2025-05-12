package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "登录管理", description = "登录管理相关接口")
public class Login {
    @PostMapping("/login")
    @Operation(summary = "登录", description = "登录")
    public ApiResponse<String> login() {
        return ApiResponse.success("登录成功");
    }
}
