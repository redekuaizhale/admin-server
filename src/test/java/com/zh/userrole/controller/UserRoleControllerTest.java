package com.zh.userrole.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zh.AdminServerApplicationTests;
import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.userrole.entity.UserRoleEntity;
import com.zh.userrole.service.UserRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class UserRoleControllerTest extends AdminServerApplicationTests {


    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void query() {
        RequestPage requestPage = new RequestPage();
        ResponsePage result = userRoleService.query(requestPage);
        List<UserRoleEntity> resultList = (List<UserRoleEntity>) result.getResultList();
        result.setResultList(resultList);

        for (UserRoleEntity entity : resultList) {
            Map<String, Object> map = BeanUtil.beanToMap(entity);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            }
        }
    }
}