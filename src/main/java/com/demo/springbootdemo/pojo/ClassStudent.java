package com.demo.springbootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassStudent {
    private String classId;
    private String className;
    private String studentId;
    private String studentName;
    private String invited;
}
