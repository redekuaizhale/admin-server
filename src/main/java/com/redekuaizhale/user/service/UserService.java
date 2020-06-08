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
package com.redekuaizhale.user.service;

import com.redekuaizhale.base.exception.ServiceException;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.company.service.CompanyService;
import com.redekuaizhale.constants.RoleConstant;
import com.redekuaizhale.constants.StatusConstant;
import com.redekuaizhale.dept.entity.DeptEntity;
import com.redekuaizhale.dept.service.DeptService;
import com.redekuaizhale.role.entity.RoleEntity;
import com.redekuaizhale.role.service.RoleService;
import com.redekuaizhale.user.dto.RequestLoginUserDTO;
import com.redekuaizhale.user.dto.RequestUserDTO;
import com.redekuaizhale.user.dto.ResponseUserDTO;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.user.repository.UserRepository;
import com.redekuaizhale.userrole.entity.UserRoleEntity;
import com.redekuaizhale.userrole.service.UserRoleService;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import com.redekuaizhale.utils.redis.RedisUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.DeclaredType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户Service
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Service
public class UserService extends BaseService<UserEntity> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    public void setRository(UserRepository userRepository) {
        super.baseRepository = userRepository;
    }

    /**
     * 获取登录人信息
     * @param request
     * @return
     */
    public ResponseUserDTO getLoginUser(RequestLoginUserDTO request) {
        Map<String, Object> queryMap = new HashMap<>(16);
        queryMap.put("loginCode", request.getLoginCode());
        queryMap.put("password", request.getPassword());
        UserEntity user = findByProperties(queryMap);
        if (user == null) {
            throw new ServiceException("用户名或密码不正确!");
        }
        if (StringUtils.equals(user.getStatus(), StatusConstant.NO_USE.getValue())) {
            throw new ServiceException("该账号已被停用！");
        }
        String token = UUID.randomUUID().toString();
        redisUtils.set(token, user.getId());

        ResponseUserDTO userDTO = new ResponseUserDTO();
        BeanUtils.copyProperties(user, userDTO);

        userDTO.setToken(token);
        return userDTO;
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    public String add(RequestUserDTO dto) {
        UserEntity entity = new UserEntity();
        BeanCopyUtils.DTOToEntity(dto, entity);
        DeptEntity deptEntity = deptService.findById(dto.getDeptId());
        entity.setDeptEntity(deptEntity);
        entity.setCompanyEntity(deptEntity.getCompanyEntity());
        save(entity);
        return entity.getId();
    }

    /**
     * 修改
     * @param dto
     */
    public void edit(RequestUserDTO dto) {
        UserEntity entity = findById(dto.getId());
        BeanCopyUtils.DTOToEntity(dto, entity);
        DeptEntity deptEntity = deptService.findById(dto.getDeptId());
        entity.setDeptEntity(deptEntity);
        entity.setCompanyEntity(deptEntity.getCompanyEntity());
        update(entity);
    }

    /**
     * 判断登录人是否是超级管理员
     * @return
     */
    public Boolean checkLoginUserIsAdmin() {
        UserEntity loginUser = getLoginUser();
        RoleEntity roleEntity = roleService.findByName(RoleConstant.ADMIN.getValue());
        if (roleEntity == null) {
            return false;
        }
        UserRoleEntity userRoleEntity = userRoleService.findByUserIdAndRoleId(loginUser.getId(), roleEntity.getId());
        return userRoleEntity != null;
    }
}
