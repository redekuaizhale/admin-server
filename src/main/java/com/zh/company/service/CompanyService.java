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
package com.zh.company.service;

import com.zh.base.param.OrderParam;
import com.zh.base.service.BaseService;
import com.zh.company.dto.CompanyDTO;
import com.zh.company.dto.RequestCompanyDTO;
import com.zh.company.dto.ResponseCompanyDTO;
import com.zh.company.entity.CompanyEntity;
import com.zh.company.repository.CompanyRepository;
import com.zh.constants.BaseEntityConstant;
import com.zh.constants.DirecttionConstant;
import com.zh.user.entity.UserEntity;
import com.zh.utils.bean.CopyBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
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
        OrderParam orderParam = OrderParam.newOrderParam("parentId", DirecttionConstant.ASC.getValue());
        return findAllByProperty("parentId", parentId, orderParam);
    }

    /**
     * 新增
     *
     * @param reqeust
     */
    public String add(RequestCompanyDTO reqeust) {
        CompanyEntity entity = new CompanyEntity();
        CopyBeanUtil.DTOToEntity(reqeust, entity);
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
        CopyBeanUtil.DTOToEntity(request, entity);
        update(entity);
    }

    /**
     * 查询机构树
     *
     * @return
     */
    public ResponseCompanyDTO findCompanyTree() {
        CompanyEntity parent = findByParentId(BaseEntityConstant.PARENT.getValue());
        ResponseCompanyDTO responseCompanyDTO = ResponseCompanyDTO.toDTO(parent);
        List<CompanyEntity> companyEntityList = findAllByParentId(parent.getId());
        List<ResponseCompanyDTO> dtoList = ResponseCompanyDTO.toDTOList(companyEntityList);
        responseCompanyDTO.setChildren(dtoList);
        return responseCompanyDTO;
    }

    /**
     * 获取当前登录人机构树
     *
     * @return
     */
    public List<ResponseCompanyDTO> findLoginUserCompanyTree() {
        CompanyEntity parent = findByParentId(BaseEntityConstant.PARENT.getValue());
        List<CompanyEntity> entityList = findAllByParentId(parent.getId());
        List<ResponseCompanyDTO> dtoList = ResponseCompanyDTO.toDTOList(entityList);
        for (ResponseCompanyDTO dto : dtoList) {
            dto.setChildren(getChild(dto.getId()));
        }
        return dtoList;
    }

    /**
     * 递归查询
     * @param parentId
     * @return
     */
    public List<ResponseCompanyDTO> getChild(String parentId) {
        List<ResponseCompanyDTO> responseCompanyDTOList = new ArrayList<>();
        List<CompanyEntity> entityList = findAllByParentId(parentId);
        if (CollectionUtils.isEmpty(entityList)) {
            return responseCompanyDTOList;
        }
        List<ResponseCompanyDTO> dtoList = ResponseCompanyDTO.toDTOList(entityList);
        for (ResponseCompanyDTO dto : dtoList) {
            dto.setChildren(getChild(dto.getId()));
        }
        return responseCompanyDTOList;
    }
}
