package com.demo.springbootdemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.common.ExcludeGlobalExceptionHandler;
import com.demo.springbootdemo.pojo.User;
import com.demo.springbootdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@Tag(name = "学生管理", description = "学生管理相关接口")
@RequestMapping("/test")
//@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class HelloWorld {
    @Autowired
    UserService userService;

    @Operation(summary = "根据ID查询用户信息", description = "根据id查询用户信息")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true)
    })
    @GetMapping("/queryUserById")
    public ApiResponse<User> queryUserById(int id) {
        return ApiResponse.success(userService.queryUserById(id));
    }

    @Operation(summary = "新增用户", description = "新增用户")
    @PostMapping("/addUser")
    public ApiResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ApiResponse.success("新增成功");
    }

    @Operation(summary = "查询用户列表", description = "查询用户列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = true),
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
    })
    @GetMapping("/queryList")
    public ApiResponse<Map<String, Object>> queryList(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Map<String, Object> map = userService.queryList(currentPage, pageSize);
        log.info("查询结果111 map：" + currentPage);
        return ApiResponse.success(map);
    }

    @Operation(summary = "删除用户", description = "根据id删除用户")
    @DeleteMapping("/deleteUserById")
    public ApiResponse<String> deleteUserById(int id) {
        return ApiResponse.success(userService.deleteUserById(id));
    }

    @Operation(summary = "批量删除用户", description = "根据id批量删除用户")
    @Parameters({
            @Parameter(name = "ids", description = "用户id列表", required = true)
    })
    @PostMapping("/deleteUserByIds")
    public ApiResponse<String> deleteUserByIds(@RequestBody Map<String, Object> request) {
        List<Integer> ids = (List<Integer>) request.get("ids");
        return ApiResponse.success(userService.deleteUserByIds(ids));
    }

    @Operation(summary = "更新用户", description = "更新用户")
    @PostMapping("/updateUser")
    public ApiResponse<String> updateUser(@RequestBody User user) {
        return ApiResponse.success(userService.updateUser(user));
    }

    //  上传图片
    @Operation(summary = "上传图片", description = "上传图片")
    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        final String uploadPath = "C:\\testProject\\SpringBootDemo\\src\\main\\resources\\static\\img";
        final String baseUrl = "http://localhost:8088/"; // 设置图片访问的基础URL
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = uploadPath + "/" + fileName;
        file.transferTo(new File(filePath));
        return ApiResponse.success(baseUrl + "img/" + fileName);
    }

    // 导出
    @Operation(summary = "导出Excel", description = "导出Excel")
    @GetMapping("/exportExcel")
    @ExcludeGlobalExceptionHandler
    public void exportExcel(HttpServletResponse response) {
        try {
            //设置响应头
            response.reset(); // 重要！清除可能存在的默认设置
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户信息表", "UTF-8") + ".xlsx");
            List<User> users = userService.queryUsersForExport();
            // 使用 EasyExcel 导出数据

            EasyExcel.write(response.getOutputStream(), User.class).sheet("用户信息表").doWrite(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "导入Excel", description = "导入Excel")
    @PostMapping("/importExcel")
    public ApiResponse<String> importExcel(@Validated @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("file" + file.isEmpty() + "----" + file.getOriginalFilename());
            if (file.isEmpty()) {
                return ApiResponse.error(400, "请选择要导入的文件");
            }
            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                return ApiResponse.error(400, "仅支持 xlsx 格式文件");
            }

            return ApiResponse.success(userService.batchInsert(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error(500, e.getMessage());
        }
    }
}

