package com.demo.springbootdemo.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "枚举信息")
public class DictItem {
    @Schema(description = "字典键", example = "01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @Schema(description = "字典值", example = "男", requiredMode = Schema.RequiredMode.REQUIRED)
    private String label;
}
