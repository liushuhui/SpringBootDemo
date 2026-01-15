package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<Teacher> queryTeachers(int offset, int limit);

    int getTotalTeachers();

    void addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacherByIds(List<Integer> ids);
}
