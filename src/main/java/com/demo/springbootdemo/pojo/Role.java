package com.demo.springbootdemo.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Schema(description = "角色id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleId;

    @Schema(description = "角色名称", example = "管理员", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleName;

    @Schema(description = "菜单权限", example = "111", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> menuIds;

    @Schema(description = "创建时间", example = "2023-05-01 00:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String createTime;

    @Schema(description = "更新时间", example = "2023-05-01 00:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String updateTime;

}
