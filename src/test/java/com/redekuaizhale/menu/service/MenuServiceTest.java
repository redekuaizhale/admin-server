package com.redekuaizhale.menu.service;

import com.redekuaizhale.AdminServerApplicationTests;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.user.service.UserService;
import com.redekuaizhale.usermenu.entity.UserMenuEntity;
import com.redekuaizhale.usermenu.service.UserMenuService;
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