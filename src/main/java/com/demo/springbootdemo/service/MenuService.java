package com.demo.springbootdemo.service;

import com.demo.springbootdemo.mapper.MenuMapper;
import com.demo.springbootdemo.pojo.Menu;
import com.demo.springbootdemo.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public Map<String, Object> getMenuList(int currentPage, int pageSize) {
        // 计算分页查询的起始位置和每页大小
        int offset = (currentPage - 1) * pageSize;

        //查询分页数据
        List<Menu> MenuList = menuMapper.getMenuList(offset, pageSize);

        //查询总数
        int total = menuMapper.getTotalMenus();

        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("total", total);
        map.put("list", MenuList);
        return map;
    }

    public void buildTree(Menu parentMenu, Map<String, Menu> menuMap) {
        List<Menu> children = menuMap.values().stream()
                .filter(menu -> menu.getParentId().equals(parentMenu.getId()))
                .sorted(Comparator.comparingInt(Menu::getSort))
                .toList();
        parentMenu.setChildren(children);
        children.forEach(child -> buildTree(child, menuMap));
    }

    public List<Menu> getMenuTree() {
        List<Menu> allMenus = menuMapper.getMenuList(0, 100);

        // 构建树形结构
        Map<String, Menu> menuMap = allMenus.stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));
        List<Menu> rootMenus = allMenus.stream()
                .filter(menu -> Objects.equals(menu.getParentId(), "0"))
                .sorted(Comparator.comparingInt(Menu::getSort))
                .collect(Collectors.toList());

        rootMenus.forEach(root -> buildTree(root, menuMap));
        return rootMenus;
    }

    public List<Menu> getMenuByParentId(Long parentId) {
        return menuMapper.getMenuByParentId(parentId);
    }

    public String addMenu(Menu menu) {
        menu.setId(IdGenerator.nextId());
        int affectedRows = menuMapper.addMenu(menu);
        return affectedRows > 0 ? "添加成功" : "添加失败";
    }

    public Menu getMenuById(Long id) {
        return menuMapper.getMenuById(id);
    }

    public String updateMenu(Menu menu) {
        int affectedRows = menuMapper.updateMenu(menu);
        return affectedRows > 0 ? "更新成功" : "更新失败";
    }

    public String deleteMenuById(Long id) {
        int affectedRows = menuMapper.deleteMenuById(id);
        return affectedRows > 0 ? "删除成功" : "删除失败";
    }
}
