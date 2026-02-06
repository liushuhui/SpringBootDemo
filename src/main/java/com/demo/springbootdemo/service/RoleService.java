package com.demo.springbootdemo.service;

import com.demo.springbootdemo.common.PageResult;
import com.demo.springbootdemo.mapper.RoleMapper;
import com.demo.springbootdemo.pojo.Role;
import com.demo.springbootdemo.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleMapper roleMapper;

    public int addRole(Role role) {
        role.setRoleId(IdGenerator.nextId());
        return roleMapper.addRole(role);
    }

    public PageResult<Role> queryRoleList(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        int total = roleMapper.getTotalUsers();
        List<Role> RoleList = roleMapper.queryRoleList(offset, pageSize);
        return new PageResult<>(currentPage, pageSize, total, RoleList);
    }

    public Integer updateRole(Role role) {
        roleMapper.deleteRoleMenus(role.getRoleId());
        return roleMapper.addRole(role);
    }
}
