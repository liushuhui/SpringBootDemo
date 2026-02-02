package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.ClassStudent;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassStudentMapper {
    List<ClassStudent> queryClassStudent(int currentPage, int pageSize, String classIds);

    int addStudentToClass(List<Integer> studentIds, String classId, String invited);

    int getTotalClassStudent();

    //检查学生是否已在班级中
    @MapKey("studentId")
    List<Map<String, Object>> checkExistingStudents(@Param("studentIds") List<Integer> studentIds, @Param("classId") String classId);

    //查询已存在的记录
    int deleteClassStudent(String studentId, String classId);
}
