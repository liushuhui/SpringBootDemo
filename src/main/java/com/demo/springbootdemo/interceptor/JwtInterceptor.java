package com.demo.springbootdemo.interceptor;

//import cn.hutool.json.ObjectMapper;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    public JwtInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String token = request.getHeader("Token");
        
        // 检查Token是否存在
        if (token == null || token.trim().isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            String json = objectMapper.writeValueAsString(ApiResponse.error(401, "缺少Token"));
            response.getWriter().write(json);
            return false;
        }
        
        // 检查Token是否已过期
        if (JwtUtil.isTokenExpired(token)) {
            response.setContentType("application/json;charset=UTF-8");
            String json = objectMapper.writeValueAsString(ApiResponse.error(401, "登录过期，请重新登录"));
            response.getWriter().write(json);
            return false;
        }
        
        // 检查Token是否有效（格式、签名等）
        if (!JwtUtil.verifyToken(token)) {
            response.setContentType("application/json;charset=UTF-8");
            String json = objectMapper.writeValueAsString(ApiResponse.error(401, "无效的Token"));
            response.getWriter().write(json);
            return false;
        }
        
        // Token有效且未过期，允许通过
        return true;
    }
}