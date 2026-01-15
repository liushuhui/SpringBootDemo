package com.demo.springbootdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    private int currentPage;
    private int pageSize;
    private int total;
    private List<T> list;
}
