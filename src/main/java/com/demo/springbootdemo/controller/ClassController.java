package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.pojo.Class;
import com.demo.springbootdemo.pojo.ClassStudent;
import com.demo.springbootdemo.pojo.TeacherInfo;
import com.demo.springbootdemo.service.ClassService;
import com.demo.springbootdemo.service.ClassStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "班级管理", description = "班级管理相关接口")
@RequestMapping("/class")
@RequiredArgsConstructor
@Slf4j
public class ClassController {
    private final ClassService classService;
    private final ClassStudentService classStudentService;

    @GetMapping("/queryClass")
    public ApiResponse<PageResult<Class>> queryClass(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "classId", required = false) String classId,
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "headerTeacherName", required = false) String headerTeacherName,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime
    ) {
        PageResult<Class> map = classService.queryClass(
                currentPage,
                pageSize,
                classId,
                className,
                headerTeacherName,
                startTime,
                endTime);
        return ApiResponse.success(map);
    }

    @PostMapping("/addClass")
    public ApiResponse<String> addClass(@RequestBody Class clazz) {
        classService.addClass(clazz);
        return ApiResponse.success("新增成功");
    }

    @GetMapping("/getTeacher")
    public ApiResponse<List<TeacherInfo>> getTeacher() {
        return ApiResponse.success(classService.getTeacher());
    }

    @PostMapping("/addStudentToClass")
    public ApiResponse<Integer> addStudentToClass(@RequestBody Map<String, Object> request) {
        List<Integer> studentIds = (List<Integer>) request.get("studentIds");
        String classId = (String) request.get("classId");
        String invited = (String) request.get("invited");
        return ApiResponse.success(classStudentService.addStudentToClass(studentIds, classId, invited));
    }

    @GetMapping("/queryClassStudent")
    public ApiResponse<PageResult<ClassStudent>> queryClassStudent(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam String classId
    ) {
        PageResult<ClassStudent> map = classStudentService.queryClassStudent(currentPage, pageSize, classId);
        return ApiResponse.success(map);
    }

    @DeleteMapping("/deleteClassStudent")
    public ApiResponse<String> deleteClassStudent(@RequestParam Map<String, Object> request) {
        String studentId = (String) request.get("studentId");
        String classId = (String) request.get("classId");
        String result = classStudentService.deleteClassStudent(studentId, classId);
        return ApiResponse.success(result);
    }
}
