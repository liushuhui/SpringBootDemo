package com.demo.springbootdemo.mapper;

import com.demo.springbootdemo.pojo.Dict;
import com.demo.springbootdemo.pojo.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper {

    //获取所有字典类型
    List<DictData> getAllDictData();

}
