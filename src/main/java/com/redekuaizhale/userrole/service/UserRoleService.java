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
package com.redekuaizhale.userrole.service;

import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.role.entity.RoleEntity;
import com.redekuaizhale.role.service.RoleService;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.user.service.UserService;
import com.redekuaizhale.userrole.dto.RequestUserRoleDTO;
import com.redekuaizhale.userrole.entity.UserRoleEntity;
import com.redekuaizhale.userrole.repository.UserRoleRepository;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class UserRoleService extends BaseService<UserRoleEntity> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public void setRository(UserRoleRepository userRoleRepository) {
        super.baseRepository = userRoleRepository;
    }

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    public List<UserRoleEntity> findByUserId(String userId) {
        return findAllByProperty("userEntity.id", userId);
    }

    /**
     * 根据用户id和角色id查询
     * @param userId
     * @param roleId
     * @return
     */
    public UserRoleEntity findByUserIdAndRoleId(String userId, String roleId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userEntity.id", userId);
        map.put("roleEntity.id", roleId);
        return findByProperties(map);
    }

    /**
     * 新增
     * @param dto
     */
    public String add(RequestUserRoleDTO dto) {
        UserRoleEntity entity = new UserRoleEntity();
        UserEntity userEntity = userService.findById(dto.getUserId());
        RoleEntity roleEntity = roleService.findById(dto.getRoleId());
        BeanCopyUtils.DTOToEntity(dto, entity);
        entity.setRoleEntity(roleEntity);
        entity.setUserEntity(userEntity);
        save(entity);
        return entity.getId();
    }

    /**
     * 按条件删除
     * @param dto
     */
    public void deleteByUserIdAndRoleId(RequestUserRoleDTO dto) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userEntity.id", dto.getUserId());
        map.put("roleEntity.id",dto.getRoleId());
        UserRoleEntity userRoleEntity = findByProperties(map);
        deleteById(userRoleEntity.getId());
    }

    /**
     * 添加用户角色
     * @param request
     */
    public void addUserRole(RequestUserRoleDTO request) {
        List<UserRoleEntity> userRoleEntityList = findByUserId(request.getUserId());
        deleteAll(userRoleEntityList);
        List<String> hasRoleIdList = request.getRoleIdList();
        if (!CollectionUtils.isEmpty(hasRoleIdList)) {
            hasRoleIdList.forEach(item->{
                request.setRoleId(item);
                add(request);
            });
        }
    }

    /**
     * 根据用户查询已有角色id
     * @param request
     * @return
     */
    public List<String> findRoleIdsByUserId(RequestUserRoleDTO request) {
        List<UserRoleEntity> userRoleEntityList = findByUserId(request.getUserId());
        if (CollectionUtils.isEmpty(userRoleEntityList)) {
            return new ArrayList<>();
        }
        return userRoleEntityList.stream().map(item -> item.getRoleEntity().getId()).collect(Collectors.toList());
    }
}
