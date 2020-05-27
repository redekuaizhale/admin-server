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
package com.redekuaizhale.company.service;

import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.company.dto.RequestCompanyDTO;
import com.redekuaizhale.company.entity.CompanyEntity;
import com.redekuaizhale.company.repository.CompanyRepository;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class CompanyService extends BaseService<CompanyEntity> {

    @Autowired
    public void setRository(CompanyRepository repository) {
        super.baseRepository = repository;
    }
    /**
     * 新增
     * @param dto
     */
    public String add(RequestCompanyDTO dto) {
        CompanyEntity entity = new CompanyEntity();
        BeanCopyUtils.DTOToEntity(dto, entity);
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     * @param dto
     */
    public void edit(RequestCompanyDTO dto) {
        CompanyEntity entity = findById(dto.getId());
        BeanCopyUtils.DTOToEntity(dto, entity);
        update(entity);
    }
}
