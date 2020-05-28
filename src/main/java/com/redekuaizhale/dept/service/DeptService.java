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
package com.redekuaizhale.dept.service;

import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.company.entity.CompanyEntity;
import com.redekuaizhale.company.service.CompanyService;
import com.redekuaizhale.dept.constant.DeptConstant;
import com.redekuaizhale.dept.dto.RequestDeptDTO;
import com.redekuaizhale.dept.entity.DeptEntity;
import com.redekuaizhale.dept.repository.DeptRepository;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param dto
     */
    public String add(RequestDeptDTO dto) {
        DeptEntity entity = new DeptEntity();
        CompanyEntity companyEntity = companyService.findById(dto.getCompanyEntity().getId());
        BeanCopyUtils.DTOToEntity(dto, entity);
        entity.setCompanyEntity(companyEntity);
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     * @param dto
     */
    public void edit(RequestDeptDTO dto) {
        DeptEntity entity = findById(dto.getId());
        BeanCopyUtils.DTOToEntity(dto, entity, "companyEntity");
        update(entity);
    }
}
