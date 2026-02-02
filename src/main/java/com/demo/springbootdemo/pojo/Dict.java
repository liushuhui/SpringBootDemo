package com.demo.springbootdemo.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字典信息")
public class Dict {
    @Schema(description = "字典类别", example = "sex", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "字典名称", example = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "字典数组", example = "1:男,2:女", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<DictItem> items;

}
