package com.redekuaizhale.service;

import com.redekuaizhale.AdminServerApplicationTests;
import com.redekuaizhale.entity.Menu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceTest extends AdminServerApplicationTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void addMenuTest(){

        List<Menu> menuList = menuService.findAllByProperty("parentId", "");

        System.out.println(menuList.size());
    }
}