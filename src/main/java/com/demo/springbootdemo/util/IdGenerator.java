package com.demo.springbootdemo.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdGenerator {
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    public static long nextId() {
        return snowflake.nextId();
    }
}
