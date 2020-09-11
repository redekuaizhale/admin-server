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
package com.zh.parameter.service;

import com.zh.base.service.BaseService;
import com.zh.parameter.dto.RequestParameterDTO;
import com.zh.parameter.entity.ParameterEntity;
import com.zh.parameter.repository.ParameterRepository;
import com.zh.utils.bean.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghui
 * @date 2020-09-09
 * @company Dingxuan
 */
@Service
public class ParameterService extends BaseService<ParameterEntity> {

    @Autowired
    public void setRository(ParameterRepository repository) {
        super.baseRepository = repository;
    }

    public String add(RequestParameterDTO dto) {
        ParameterEntity entity = new ParameterEntity();
        CopyBeanUtil.DTOToEntity(dto, entity);
        save(entity);
        return entity.getId();
    }

    public void edit(RequestParameterDTO dto) {
        ParameterEntity entity = findById(dto.getId());
        CopyBeanUtil.DTOToEntity(dto, entity);
        save(entity);
    }

    public ParameterEntity findByKey(RequestParameterDTO dto) {
        return findByProperty("parameterKey",dto.getParameterKey());
    }
}
