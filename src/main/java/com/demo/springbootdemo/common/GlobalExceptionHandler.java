package com.demo.springbootdemo.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 捕获所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<String> handleException(Exception e, HandlerMethod handlerMethod) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 添加响应类型检查
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            if (response.getContentType() != null && response.getContentType().startsWith("application/vnd.ms-excel")) {
                throw new RuntimeException(e); // 如果是Excel响应类型，直接抛出原始异常
            }
            if (handlerMethod != null) {
                boolean shouldExclude = handlerMethod.getMethod().isAnnotationPresent(ExcludeGlobalExceptionHandler.class)
                        || handlerMethod.getBeanType().isAnnotationPresent(ExcludeGlobalExceptionHandler.class);

                if (shouldExclude) {
                    throw e; // 直接抛出原始异常
                }
            }
            return ApiResponse.error(500, "服务器错误：" + e.getMessage());
        } catch (Exception ex) {
            return ApiResponse.error(500, "异常处理过程中发生错误:" + ex.getMessage());
        }
    }

}
