/**
 * Copyright Dingxuan. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zh.dept.service;

import com.zh.base.param.OrderParam;
import com.zh.base.service.BaseService;
import com.zh.company.entity.CompanyEntity;
import com.zh.company.service.CompanyService;
import com.zh.constants.DirecttionConstant;
import com.zh.dept.dto.RequestDeptDTO;
import com.zh.dept.entity.DeptEntity;
import com.zh.dept.repository.DeptRepository;
import com.zh.utils.bean.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class DeptService extends BaseService<DeptEntity> {

    @Autowired
    private CompanyService companyService;

    @Autowired
    public void setRository(DeptRepository repository) {
        super.baseRepository = repository;
    }

    /**
     * 新增
     *
     * @param dto
     */
    public String add(RequestDeptDTO dto) {
        DeptEntity entity = new DeptEntity();
        BeanCopyUtils.DTOToEntity(dto, entity);
        CompanyEntity companyEntity = companyService.findById(dto.getCompanyId());
        entity.setCompanyEntity(companyEntity);
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     *
     * @param dto
     */
    public void edit(RequestDeptDTO dto) {
        DeptEntity entity = findById(dto.getId());
        BeanCopyUtils.DTOToEntity(dto, entity);
        update(entity);
    }

    /**
     * 根据机构id查询
     * @param dto
     * @return
     */
    public List<DeptEntity> findByCompanyId(RequestDeptDTO dto) {
        OrderParam orderParam = OrderParam.newOrderParam("deptOrder", DirecttionConstant.ASC.getValue());
        return findAllByProperty("companyEntity.id", dto.getCompanyId(), orderParam);
    }
}
