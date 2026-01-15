package com.demo.springbootdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学生信息")
public class Student {
    @Schema(description = "id", example = "1")
    @ExcelProperty(value = "id", index = 0)
    @NotNull(message = "id不能为空")
    private int id;

    @Schema(description = "学生姓名", example = "tom", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "姓名", index = 1)
    @NotNull(message = "姓名不能为空")
    private String name;

    @Schema(description = "头像", example = "https://www.baidu.com/img/bd_logo1.png")
    @ExcelProperty(value = "头像", index = 2)
    @NotNull(message = "头像不能为空")
    private String avatar;

    @Schema(description = "年龄", example = "18")
    @ExcelProperty(value = "年龄", index = 3)
    @NotNull(message = "年龄不能为空")
    private int age;

    @Schema(description = "性别", example = "1")
    @ExcelProperty(value = "性别", index = 4)
    @NotNull(message = "性别不能为空")
    private int gender;

    @Schema(description = "年级", example = "1")
    @ExcelProperty(value = "年级", index = 5)
    @NotNull(message = "年级不能为空")
    private int grade;

    @Schema(description = "分数", example = "100")
    @ExcelProperty(value = "分数", index = 6)
    @NotNull(message = "分数不能为空")
    private int score;

    @Schema(description = "创建时间", example = "2023-01-01 00:00:00")
    @ExcelProperty(value = "创建时间", index = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "更新时间", example = "2023-01-01 00:00:00")
    @ExcelProperty(value = "更新时间", index = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
