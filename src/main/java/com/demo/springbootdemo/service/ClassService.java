package com.demo.springbootdemo.service;

import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.mapper.ClassMapper;
import com.demo.springbootdemo.pojo.Class;
import com.demo.springbootdemo.pojo.TeacherInfo;
import com.demo.springbootdemo.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassService {
    private final ClassMapper classMapper;

    public PageResult<Class> queryClass(
            int currentPage,
            int pageSize,
            String classId,
            String className,
            String headerTeacherName,
            String startTime,
            String endTime
    ) {
        // 获取分页查询的起始位置和每页大小
        int offset = (currentPage - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("classId", classId);
        params.put("className", className);
        params.put("headerTeacherName", headerTeacherName);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("offset", offset);
        params.put("limit", pageSize);
        // 查询分页数据
        List<Class> classList = classMapper.queryClass(params);

        // 查询总数
        int total = classMapper.getTotalClass();

        return new PageResult<>(currentPage, pageSize, total, classList);
    }

    public void addClass(Class clazz) {
        clazz.setClassId(IdGenerator.nextId());
        classMapper.addClass(clazz);
    }

    public List<TeacherInfo> getTeacher() {
        return classMapper.getTeacher();
    }
}
