package com.zh.dept.service;

import com.zh.AdminServerApplicationTests;
import com.zh.company.entity.CompanyEntity;
import com.zh.company.service.CompanyService;
import com.zh.dept.entity.DeptEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DeptServiceTest extends AdminServerApplicationTests {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DeptService deptService;


    @Test
    public void add() {
        CompanyEntity companyEntity = companyService.findById("4028b881726d849b01726ea5412d0001");
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setCompanyEntity(companyEntity);
        deptEntity.setName("部门1");
        deptService.save(deptEntity);
    }
}