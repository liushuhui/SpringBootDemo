package com.demo.springbootdemo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor// 自动生成无参构造方法
//@JsonInclude(JsonInclude.Include.NON_NULL) // 排除 null 值
public class ApiResponse<T> {
    private int code; //状态码
    private String message; // 提示信息
    private T data; //返回数据
    private boolean isSuccess;

    // 构造方法
    public ApiResponse(int code, String message, T data, boolean isSuccess) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    // 静态方法，用于快速生成成功响应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "成功", data, true);
    }

    // 静态方法，用于快速生成失败响应
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null, false);
    }

}
