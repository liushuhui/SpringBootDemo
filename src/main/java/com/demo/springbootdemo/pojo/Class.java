package com.demo.springbootdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "班级信息")
public class Class {
    @Schema(description = "班级id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String classId;

    @Schema(description = "班级名称", example = "1-1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String className;

    @Schema(description = "班主任Id", example = "刘老师", requiredMode = Schema.RequiredMode.REQUIRED)
    private String headerTeacherId;

    @Schema(description = "班级任名称", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private String headerTeacherName;

    @Schema(description = "创建时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "更新时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
