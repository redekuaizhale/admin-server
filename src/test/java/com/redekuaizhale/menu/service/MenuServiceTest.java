package com.redekuaizhale.menu.service;

import com.redekuaizhale.AdminServerApplicationTests;
import com.redekuaizhale.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;


public class MenuServiceTest extends AdminServerApplicationTests {

    @Autowired
    private MenuService menuService;


    public void addTest() {

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName("菜单管理");

        menuEntity.setParentId("297e0d276e102bd6016e102be38c0000");
        menuEntity.setComponent("");
    }


}