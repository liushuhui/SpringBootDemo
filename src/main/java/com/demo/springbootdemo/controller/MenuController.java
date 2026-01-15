package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.pojo.Menu;
import com.demo.springbootdemo.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "菜单管理", description = "菜单管理相关接口")
@RequestMapping("/menu")
@Slf4j
public class MenuController {
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
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize
    ) {
        Map<String, Object> map = menuService.getMenuList(currentPage, pageSize);
        return ApiResponse.success(map);
    }

    @Operation(summary = "查询菜单树", description = "查询菜单树")
    @GetMapping("/getMenuTree")
    public ApiResponse<List<Menu>> getMenuTree() {
        List<Menu> map = menuService.getMenuTree();
        return ApiResponse.success(map);
    }

    @Operation(summary = "添加菜单", description = "添加菜单")
    @PostMapping("/addMenu")
    public ApiResponse<String> addMenu(@RequestBody Menu menu) {
        String addMenuData = menuService.addMenu(menu);
        return ApiResponse.success(addMenuData);
    }

    @Operation(summary = "查询父菜单", description = "查询父菜单")
    @GetMapping("/getMenuByParentId")
    public ApiResponse<List<Menu>> getMenuByParentId(
            @RequestParam(value = "parentId", defaultValue = "0") Long parentId
    ) {
        List<Menu> map = menuService.getMenuByParentId(parentId);
        return ApiResponse.success(map);
    }

    @Operation(summary = "查询菜单", description = "查询菜单")
    @GetMapping("/getMenuById")
    public ApiResponse<Menu> getMenuById(
            @RequestParam(value = "id", defaultValue = "0") Long id
    ) {
        Menu map = menuService.getMenuById(id);
        return ApiResponse.success(map);
    }

    @Operation(summary = "编辑菜单", description = "编辑菜单")
    @PostMapping("/updateMenu")
    public ApiResponse<String> updateMenu(@RequestBody Menu menu) {
        String updateMenuData = menuService.updateMenu(menu);
        return ApiResponse.success(updateMenuData);
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping("/deleteMenuById")
    public ApiResponse<String> deleteMenuById(
            @RequestParam(value = "id", defaultValue = "0") Long id
    ) {
        String deleteMenuData = menuService.deleteMenuById(id);
        return ApiResponse.success(deleteMenuData);
    }
}
