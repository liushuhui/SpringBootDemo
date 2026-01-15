package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository
public interface StudentMapper {
    // 分页查询用户
    List<Student> queryList(int offset, int limit);

    // 查询总数
    int getTotalUsers();

    Student queryUserById(int id);

    void addUser(Student student);

    int deleteUserById(int id);

    int deleteUserByIds(@Param("ids") List<Integer> ids);

    int updateUser(Student student);

    List<Student> queryUsersForExport();

    int batchInsert(@Param("list") List<Student> list);
}
