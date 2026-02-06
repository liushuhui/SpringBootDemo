package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.pojo.Role;
import com.demo.springbootdemo.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "角色管理", description = "角色管理相关接口")
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "查询角色列表", description = "查询角色列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = true),
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
    })
    @GetMapping("queryRoleList")
    public ApiResponse<PageResult<Role>> queryRoleList(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize
    ) {
        PageResult<Role> map = roleService.queryRoleList(currentPage, pageSize);
        return ApiResponse.success(map);
    }

    @PostMapping("/addRole")
    public ApiResponse<Integer> addRole(@RequestBody Role role) {
        return ApiResponse.success(roleService.addRole(role));
    }

    @PostMapping("/updateRole")
    public ApiResponse<Integer> updateRole(@RequestBody Role role) {
        return ApiResponse.success(roleService.updateRole(role));
    }
}
