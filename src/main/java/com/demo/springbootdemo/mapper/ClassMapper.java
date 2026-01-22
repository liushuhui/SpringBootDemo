package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Class;
import com.demo.springbootdemo.pojo.TeacherInfo;

import java.util.List;
import java.util.Map;

public interface ClassMapper {
    List<Class> queryClass(Map<String, Object> params);

    int getTotalClass();

    void addClass(Class clazz);

    List<TeacherInfo> getTeacher();
}
