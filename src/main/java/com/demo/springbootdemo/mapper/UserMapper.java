package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository
public interface UserMapper {
    // 分页查询用户
    List<User> queryList(int offset, int limit);

    // 查询总数
    int getTotalUsers();

    User queryUserById(int id);

    void addUser(User user);

    int deleteUserById(int id);

    int deleteUserByIds(@Param("ids") List<Integer> ids);

    int updateUser(User user);

    List<User> queryUsersForExport();

    int batchInsert(@Param("list") List<User> list);
}
