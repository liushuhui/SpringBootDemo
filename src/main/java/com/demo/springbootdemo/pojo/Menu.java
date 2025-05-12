package com.demo.springbootdemo.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜单信息")
public class Menu {
    @Schema(description = "菜单ID", example = "1")
    private String id;

    @Schema(description = "菜单名称", example = "系统管理")
    private String name;

    @Schema(description = "父菜单ID", example = "0")
    private  String  parentId;

    @Schema(description = "菜单图标", example = "icon")
    private String icon;

    @Schema(description = "菜单URL", example = "/system")
    private String url;

    @Schema(description = "菜单排序", example = "1")
    private Integer sort;

    @Schema(description = "菜单状态", example = "1")
    private String enable;

    @Schema(description = "创建时间", example = "2023-01-01 00:00:00")
    private Date createTime;

    @Schema(description = "更新时间", example = "2023-01-01 00:00:00")
    private Date updateTime;

    private List<Menu> children = new ArrayList<>();
}
