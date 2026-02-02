package com.demo.springbootdemo.service;

import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.mapper.ClassStudentMapper;
import com.demo.springbootdemo.pojo.ClassStudent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassStudentService {
    private final ClassStudentMapper classStudentMapper;

    public PageResult<ClassStudent> queryClassStudent(int currentPage, int pageSize, String classId) {
        int offset = (currentPage - 1) * pageSize;
        int total = classStudentMapper.getTotalClassStudent();
        List<ClassStudent> classStudentList = classStudentMapper.queryClassStudent(offset, pageSize, classId);
        return new PageResult<>(currentPage, pageSize, total, classStudentList);
    }


    public List<Map<String, Object>> checkExistingStudents(List<Integer> studentIds, String classId) {
        return classStudentMapper.checkExistingStudents(studentIds, classId);
    }

    public Object addStudentToClass(List<Integer> studentIds, String classId, String invited) {
        return classStudentMapper.addStudentToClass(studentIds, classId, invited);
    }

    public String deleteClassStudent(String studentId, String classId) {
        int affectedRows = classStudentMapper.deleteClassStudent(studentId, classId);
        return affectedRows > 0 ? "删除成功" : "删除失败";
    }
}
