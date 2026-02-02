package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.common.ApiResponse;
import com.demo.springbootdemo.pojo.Dict;
import com.demo.springbootdemo.pojo.DictItem;
import com.demo.springbootdemo.service.DictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "字典", description = "字典相关接口")
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class DictController {

    private final DictService dictService;

    @GetMapping("/getDict")
    public ApiResponse<Map<String, List<DictItem>>> getDict() {
        Map<String, List<DictItem>> dict = dictService.getAllDict();
        return ApiResponse.success(dict);
    }
}
