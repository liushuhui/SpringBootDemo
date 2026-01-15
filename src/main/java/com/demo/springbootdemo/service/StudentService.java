package com.demo.springbootdemo.service;

import com.alibaba.excel.EasyExcel;
import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.pojo.Student;
import com.demo.springbootdemo.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;

    public PageResult<Student> queryList(int currentPage, int pageSize) {
        // 计算分页查询的起始位置和每页大小
        int offset = (currentPage - 1) * pageSize;

        //查询分页数据
        List<Student> studentList = studentMapper.queryList(offset, pageSize);

        //查询总数
        int total = studentMapper.getTotalUsers();

        return new PageResult<> (currentPage, pageSize, total, studentList);
    }

    public Student queryUserById(int id) {
        return studentMapper.queryUserById(id);
    }

    public void addUser(Student student) {
        studentMapper.addUser(student);
    }

    public String deleteUserById(int id) {
        int rows = studentMapper.deleteUserById(id);
        System.out.println("rows" + rows);
        if (rows > 0) return "删除成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public String deleteUserByIds(List<Integer> ids) {
        int rows = studentMapper.deleteUserByIds(ids);
        if (rows > 0) return "删除成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public String updateUser(Student student) {
        int rows = studentMapper.updateUser(student);
        if (rows > 0) return "修改成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public List<Student> queryUsersForExport() {
        return studentMapper.queryUsersForExport();
    }

    @Transactional
    public String batchInsert(MultipartFile file) {
        try {
            List<Student> students = EasyExcel.read(file.getInputStream())
                    .head(Student.class)
                    .sheet()
                    .doReadSync();
            if (students.isEmpty()) {
                throw new IllegalArgumentException("文件内容为空");
            }
            int affectedRows = studentMapper.batchInsert(students);
            return "成功导入 " + affectedRows + " 条数据";
        } catch (IOException e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }
}
