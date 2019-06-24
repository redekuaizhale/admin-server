package com.redekuaizhale.service;

import com.redekuaizhale.AdminServerApplicationTests;
import com.redekuaizhale.dto.usermenu.ResponseUserAllMenuDTO;
import com.redekuaizhale.entity.Menu;
import com.redekuaizhale.entity.User;
import com.redekuaizhale.entity.UserMenu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMenuServiceTest extends AdminServerApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserMenuService userMenuService;

    @Test
    public void addUserMenuTest(){
        User user = userService.findById("297e0d276b0d5c48016b0d5c50900000");
        Menu menu = menuService.findById("297e0d276b4ec3d3016b4ec3df270000");
        UserMenu userMenu = new UserMenu();
        userMenu.setUser(user);
        userMenu.setMenu(menu);
        userMenuService.add(userMenu);
    }

    @Test
    public void queryAllMenuByUserId() {
        List<ResponseUserAllMenuDTO> responseUserAllMenuDTOS = userMenuService.queryAllMenuByUserId("11");
        System.out.println(responseUserAllMenuDTOS);
    }
}