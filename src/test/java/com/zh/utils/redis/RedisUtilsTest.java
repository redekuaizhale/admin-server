package com.zh.utils.redis;

import cn.hutool.core.date.DateUtil;
import com.zh.AdminServerApplicationTests;
import org.junit.Test;

import java.util.Date;

public class RedisUtilsTest extends AdminServerApplicationTests {


    @Test
    public void test() {
        Date current = DateUtil.date();

        String format = DateUtil.format(current, "");


    }

}