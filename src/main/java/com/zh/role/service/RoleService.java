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
package com.zh.role.service;

import com.zh.base.service.BaseService;
import com.zh.role.dto.RequestRoleDTO;
import com.zh.role.entity.RoleEntity;
import com.zh.role.repository.RoleRepository;
import com.zh.utils.bean.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class RoleService extends BaseService<RoleEntity>{

    @Autowired
    public void setRository(RoleRepository repository) {
        super.baseRepository = repository;
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public RoleEntity findByName(String name) {
        return findByProperty("name", name);
    }

    /**
     * 新增
     * @param dto
     */
    public String add(RequestRoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        CopyBeanUtil.DTOToEntity(dto, entity);
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     * @param dto
     */
    public void edit(RequestRoleDTO dto) {
        RoleEntity entity = findById(dto.getId());
        CopyBeanUtil.DTOToEntity(dto, entity);
        update(entity);
    }
}
