package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.springbootdemo.pojo.Menu;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "菜单管理", description = "菜单管理相关接口")
@RequestMapping("/menu")
@Slf4j
public class MenuContorller {
    @Autowired
    MenuService menuService;

    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = true),
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
    })
    @GetMapping("/getMenu")
    public ApiResponse<Map<String, Object>> getMenuList(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Map<String, Object> map = menuService.getMenuList(currentPage, pageSize);
        log.info("查询结果111 map：{}", currentPage);
        return ApiResponse.success(map);
    }

    @GetMapping("/getMenuTree")
    public ApiResponse<Map<String, Object>> getMenuTree() {
        Map<String, Object> map = menuService.getMenuTree();
        log.info("查询结果222 map：{}", map);
        return ApiResponse.success(map);
    }
}
