package com.redekuaizhale.service;

import com.redekuaizhale.AdminServerApplicationTests;
import com.redekuaizhale.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserServiceTest extends AdminServerApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        User user = new User();
        user.setRealName("张三");
        user.setPhone("17343009527");
        userService.add(user);
    }

    @Test
    public void findByNameTest(){
        User byProperty = userService.findByProperty("realName", "张三");
        System.out.println(byProperty);
    }

    @Test
    public void findAllByNameTest(){
        List<User> allByProperty = userService.findAllByProperty("realName", "张三");
        System.out.println(allByProperty);
    }

    @Test
    public void findManyFieldTest(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("realName", "张三");
        queryMap.put("phone", "17343009527");
        User user = userService.findByProperties(queryMap);
        System.out.println(user);
    }

    @Test
    public void findManyFieldAllTest(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("realName", "张三");
        queryMap.put("phone", "17343009527");
        List<User> userList = userService.findAllByProperties(queryMap);
        System.out.println(userList);
    }

    @Test
    public void logbackTest(){
        log.error("error测试");
        log.info("info测试");
        log.warn("warn测试");
        log.error("error测试");
        log.debug("debug测试");
    }
}