package com.demo.springbootdemo.service;

import com.alibaba.excel.EasyExcel;
import com.demo.springbootdemo.pojo.User;
import com.demo.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Map<String, Object> queryList(int currentPage, int pageSize) {
        // 计算分页查询的起始位置和每页大小
        int offset = (currentPage - 1) * pageSize;

        //查询分页数据
        List<User> userList = userMapper.queryList(offset, pageSize);

        //查询总数
        int total = userMapper.getTotalUsers();

        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("total", total);
        map.put("list", userList);
        return map;
    }

    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public String deleteUserById(int id) {
        int rows = userMapper.deleteUserById(id);
        System.out.println("rows" + rows);
        if (rows > 0) return "删除成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public String deleteUserByIds(List<Integer> ids) {
        int rows = userMapper.deleteUserByIds(ids);
        if (rows > 0) return "删除成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public String updateUser(User user) {
        int rows = userMapper.updateUser(user);
        if (rows > 0) return "修改成功";
        else {
            throw new RuntimeException("用户不存在");
        }
    }

    public List<User> queryUsersForExport() {
        return userMapper.queryUsersForExport();
    }

    @Transactional
    public String batchInsert(MultipartFile file) {
        try {
            List<User> users = EasyExcel.read(file.getInputStream())
                    .head(User.class)
                    .sheet()
                    .doReadSync();
            if (users.isEmpty()) {
                throw new IllegalArgumentException("文件内容为空");
            }
            int affectedRows = userMapper.batchInsert(users);
            return "成功导入 " + affectedRows + " 条数据";
        } catch (IOException e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }
}
