package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int addRole(Role role);

    List<Role> queryRoleList(int offset, int limit );

    int getTotalUsers();

    int deleteRoleMenus(String roleId);
}
