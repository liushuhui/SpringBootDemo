package com.demo.springbootdemo.service;

import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.mapper.TeacherMapper;
import com.demo.springbootdemo.pojo.Teacher;
import com.demo.springbootdemo.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper teacherMapper;

    public PageResult<Teacher> queryTeachers(int currentPage, int pageSize) {
        // 计算分页查询的起始位置和每页大小
        int offset = (currentPage - 1) * pageSize;

        //查询分页数据
        List<Teacher> teacherList = teacherMapper.queryTeachers(offset, pageSize);

        //查询总数
        int total = teacherMapper.getTotalTeachers();


        return new PageResult<>(currentPage, pageSize, total, teacherList);
    }

    public void addTeacher(Teacher teacher) {
        teacher.setId(IdGenerator.nextId());
        teacherMapper.addTeacher(teacher);
    }

    public String updateTeacher(Teacher teacher) {
        int rows = teacherMapper.updateTeacher(teacher);
        if (rows > 0) return "修改成功";
        else {
            throw new RuntimeException("老师不存在");
        }
    }

    public String deleteTeacherByIds(List<Integer> ids) {
        int rows = teacherMapper.deleteTeacherByIds(ids);
        if (rows > 0) return "删除成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }
}
