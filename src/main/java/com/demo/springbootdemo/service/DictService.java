package com.demo.springbootdemo.service;

import com.demo.springbootdemo.mapper.DictMapper;
import com.demo.springbootdemo.pojo.Dict;
import com.demo.springbootdemo.pojo.DictData;
import com.demo.springbootdemo.pojo.DictItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DictService {
    private final DictMapper dictMapper;

    public Map<String, List<DictItem>> getAllDict() {
        // 查询所有字典数据
        List<DictData> dictDataList = dictMapper.getAllDictData();

        if (dictDataList == null || dictDataList.isEmpty()) {
            return new HashMap<>();
        }

        //按字典类型分组
        Map<String, List<DictData>> groupedData = dictDataList.stream()
                .collect(Collectors.groupingBy(DictData::getType));

        // 转换为Dict对象列表
        Map<String, List<DictItem>> result = new HashMap<>();
        groupedData.forEach((type, items) -> {

            // 将每个类型的数据转换为DictItem列表
            List<DictItem> dictItemList = items.stream()
                    .map(data -> new DictItem(data.getValue(),data.getLabel()))
                    .toList();
            result.put(type, dictItemList);
        });

        return result;
    }

}
