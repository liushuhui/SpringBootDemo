package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.pojo.Teacher;
import com.demo.springbootdemo.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "教师管理", description = "教师管理相关接口")
@RequestMapping("/teacher")
@Slf4j
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "查询教师列表", description = "查询教师列表")
    @Parameters({
            @Parameter(name = "currentPage", description = "当前页码", required = true),
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
    })
    @GetMapping("/queryTeachers")
    public ApiResponse<PageResult<Teacher>> queryTeachers(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        PageResult<Teacher> map = teacherService.queryTeachers(currentPage, pageSize);
        return ApiResponse.success(map);
    }

    @Operation(summary = "新增教师", description = "新增教师")
    @Parameters({
            @Parameter(name = "name", description = "教师信息", required = true),
            @Parameter(name = "classId", description = "班级ID", required = true),
    })
    @PostMapping("/addTeacher")
    public ApiResponse<String> addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ApiResponse.success("新增成功");
    }

    @Operation(summary = "编辑老师", description = "编辑老师")
    @Parameters({
            @Parameter(name = "id", description = "老师ID", required = true),
            @Parameter(name = "name", description = "老师名称", required = true),
            @Parameter(name = "classId", description = "班级ID", required = true),
    })
    @PostMapping("/updateTeacher")
    public ApiResponse<String> updateTeacher(@RequestBody Teacher teacher) {
        return ApiResponse.success(teacherService.updateTeacher(teacher));
    }

    @Operation(summary = "删除老师", description = "删除老师")
    @Parameters({
            @Parameter(name = "ids", description = "老师ID列表", required = true)
    })
    @PostMapping("/deleteTeacherByIds")
    public ApiResponse<String> deleteTeacherByIds(@RequestBody Map<String, Object> request) {
        List<Integer> ids = (List<Integer>) request.get("ids");
        return ApiResponse.success(teacherService.deleteTeacherByIds(ids));
    }
}
