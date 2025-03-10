package com.demo.springbootdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ExcelProperty(value = "id", index = 0)
    @NotNull(message = "id不能为空")
    private int id;

    @ExcelProperty(value = "姓名", index = 1)
    @NotNull(message = "姓名不能为空")
    private String name;

    @ExcelProperty(value = "头像", index = 2)
    @NotNull(message = "头像不能为空")
    private String avatar;

    @ExcelProperty(value = "年龄", index = 3)
    @NotNull(message = "年龄不能为空")
    private int age;

    @ExcelProperty(value = "性别", index = 4)
    @NotNull(message = "性别不能为空")
    private int gender;

    @ExcelProperty(value = "年级", index = 5)
    @NotNull(message = "年级不能为空")
    private int grade;

    @ExcelProperty(value = "分数", index = 6)
    @NotNull(message = "分数不能为空")
    private int score;

    @ExcelProperty(value = "创建时间", index = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ExcelProperty(value = "更新时间", index = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
