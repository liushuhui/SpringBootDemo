package com.demo.springbootdemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "教师信息")
public class Teacher {

    @Schema(description = "id", example = "1")
    @NotNull(message = "id不能为空")
    private String id;

    @Schema(description = "教师姓名", example = "tom", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "姓名不能为空")
    private String name;

//    @Schema(description = "班级", example = "测试班级", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "班级不能为空")
//    private String className;

    @Schema(description = "学生", example = "学生Id1, 学生Id2")
    @NotNull(message = "学生不能为空")
    private String student;

    @Schema(description = "创建时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "更新时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
