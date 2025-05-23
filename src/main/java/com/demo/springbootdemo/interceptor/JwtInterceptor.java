package com.demo.springbootdemo.interceptor;

//import cn.hutool.json.ObjectMapper;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
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
        if (token != null && JwtUtil.verifyToken(token)) {
            return true;
        }
        if (JwtUtil.isTokenExpired(token)) {
            response.setContentType("application/json;charset=UTF-8");
            String json = objectMapper.writeValueAsString(ApiResponse.error(401, "登录过期，请重新登录"));
            response.getWriter().write(json);
            return false;
        } else {
            response.setContentType("application/json;charset=UTF-8");
            try {
                String json = objectMapper.writeValueAsString(ApiResponse.error(401, "无效的Token"));
                response.getWriter().write(json);
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "序列化错误");
            }
            return false;
        }
    }
}
