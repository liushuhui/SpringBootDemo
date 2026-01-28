package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.ClassStudent;

import java.util.List;

public interface ClassStudentMapper {
    List<ClassStudent> queryClassStudent(int currentPage, int pageSize, String classIds);

    int addStudentToClass(List<Integer> studentIds, String classId, String invited);
    int getTotalClassStudent();

    int deleteClassStudent(String studentId, String classId);
}
