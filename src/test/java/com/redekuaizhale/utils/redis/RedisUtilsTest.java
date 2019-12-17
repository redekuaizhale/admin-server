package com.redekuaizhale.utils.redis;

import com.redekuaizhale.AdminServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilsTest extends AdminServerApplicationTests {

    @Autowired
    private RedisUtils redisUtils;


    @Test
    public void test11() {
        redisUtils.set("13123", "3213123");
    }

}