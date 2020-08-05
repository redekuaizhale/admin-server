package com.zh.menu.service;

import com.zh.AdminServerApplicationTests;
import com.zh.menu.entity.MenuEntity;
import com.zh.user.entity.UserEntity;
import com.zh.user.service.UserService;
import com.zh.usermenu.entity.UserMenuEntity;
import com.zh.usermenu.service.UserMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MenuServiceTest extends AdminServerApplicationTests {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserMenuService userMenuService;

    @Autowired
    private UserService userService;


    @Test
    public void addTest() {

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName("home");
        menuEntity.setPath("/home");
        menuEntity.setTitle("首页");
        menuEntity.setIcon("_menu-sys");
        menuEntity.setParentId("4028b881727450ca01727450dcad0000");
        menuEntity.setComponent("/home/home");
        menuEntity.setUseFlag("可用");
        menuService.save(menuEntity);
    }


    @Test
    public void findByParentId() {
        UserEntity userEntity = userService.findById("4028859a6dc82cbe016dc82ccc280000");
        MenuEntity menuEntity = menuService.findById("4028b8817278067201727808a9d90000");
        UserMenuEntity userMenuEntity = new UserMenuEntity();
        userMenuEntity.setUserEntity(userEntity);
        userMenuEntity.setMenuEntity(menuEntity);
        userMenuService.save(userMenuEntity);
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findByPath() {
    }

    @Test
    public void add() {
    }

    @Test
    public void edit() {
    }
}