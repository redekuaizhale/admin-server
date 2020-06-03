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

import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.company.dto.CompanyDTO;
import com.redekuaizhale.company.dto.RequestCompanyDTO;
import com.redekuaizhale.company.entity.CompanyEntity;
import com.redekuaizhale.company.repository.CompanyRepository;
import com.redekuaizhale.constants.BaseEntityConstant;
import com.redekuaizhale.constants.DirecttionConstant;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public Boolean loginUserIsHeadCompany() {
        UserEntity loginUser = getLoginUser();
        return false;
    }

    public CompanyEntity findByParentId(String parentId) {
        return findByProperty("parentId", parentId);
    }

    public List<CompanyEntity> findAllByParentId(String parentId) {
        return findAllByProperty("parentId", parentId, OrderParam.newOrderParam("parentId", DirecttionConstant.ASC.getValue()));
    }

    /**
     * 新增
     *
     * @param reqeust
     */
    public String add(RequestCompanyDTO reqeust) {
        CompanyEntity entity = new CompanyEntity();
        BeanCopyUtils.DTOToEntity(reqeust, entity);
        save(entity);
        String path;
        if (StringUtils.equals(BaseEntityConstant.PARENT.getValue(), reqeust.getParentId())) {
            path = entity.getId();
        } else {
            path = StringUtils.join(reqeust.getParentId(), DirecttionConstant.SPLIT.getValue(), entity.getId());
        }
        entity.setPath(path);
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     *
     * @param request
     */
    public void edit(RequestCompanyDTO request) {
        CompanyEntity entity = findById(request.getId());
        BeanCopyUtils.DTOToEntity(request, entity);
        update(entity);
    }

    /**
     * 查询机构树
     *
     * @return
     */
    public CompanyDTO findCompanyTree() {
        CompanyEntity parent = findByParentId(BaseEntityConstant.PARENT.getValue());
        CompanyDTO dto = BeanCopyUtils.entityToDTO(parent, CompanyDTO.class);
        List<CompanyEntity> companyEntityList = findAllByParentId(parent.getId());
        List<CompanyDTO> childList = BeanCopyUtils.entityListToDTOList(companyEntityList, CompanyDTO.class);
        dto.setChildren(childList);
        return dto;
    }

    /**
     * 获取当前登录人机构树
     *
     * @return
     */
    public List<CompanyDTO> findLoginUserCompanyTree() {
        List<CompanyDTO> list = new ArrayList<>();
        CompanyEntity parent = findByParentId(BaseEntityConstant.PARENT.getValue());
        CompanyDTO dto = BeanCopyUtils.entityToDTO(parent, CompanyDTO.class);
        List<CompanyEntity> companyEntityList = findAllByParentId(parent.getId());
        List<CompanyDTO> childList = BeanCopyUtils.entityListToDTOList(companyEntityList, CompanyDTO.class);
        dto.setChildren(childList);
        list.add(dto);
        return list;
    }

}
