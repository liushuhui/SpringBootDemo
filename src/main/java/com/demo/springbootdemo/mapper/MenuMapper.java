package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenuList(int offset, int limit);

    int addMenu(Menu menu);

    int getTotalMenus();

    List<Menu> getMenuByParentId(Long parentId);

    Menu getMenuById(Long id);

    int updateMenu(Menu menu);

    int deleteMenuById(Long id);
}
