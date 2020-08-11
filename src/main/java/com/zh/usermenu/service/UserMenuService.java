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
package com.zh.usermenu.service;

import com.zh.base.exception.ServiceException;
import com.zh.base.param.OrderParam;
import com.zh.base.service.BaseService;
import com.zh.constants.BaseEntityConstant;
import com.zh.constants.DirecttionConstant;
import com.zh.constants.MapConstant;
import com.zh.constants.RootConstant;
import com.zh.menu.entity.MenuEntity;
import com.zh.menu.service.MenuService;
import com.zh.user.entity.UserEntity;
import com.zh.user.service.UserService;
import com.zh.usermenu.dto.RequestUserMenuDTO;
import com.zh.usermenu.dto.ResponseUserMenuDTO;
import com.zh.usermenu.entity.UserMenuEntity;
import com.zh.usermenu.repository.UserMenuRepository;
import com.zh.utils.bean.CopyBeanUtil;
import com.zh.utils.threadlocal.UserThreadLocalUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户菜单Service
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class UserMenuService extends BaseService<UserMenuEntity>{

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    public void setRository(UserMenuRepository userMenuRepository) {
        super.baseRepository = userMenuRepository;
    }

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    public List<UserMenuEntity> findByUserId(String userId) {
        OrderParam orderParam = OrderParam.newOrderParam("menuEntity.menuOrder", DirecttionConstant.ASC.getValue());
        return findAllByProperty("userEntity.id",userId,orderParam);
    }

    /**
     * 根据userId和parentId查询
     * @param userId
     * @param parentId
     * @return
     */
    public List<UserMenuEntity> findByUserIdAndParentId(String userId, String parentId) {
        Map<String, Object> map = new HashMap<>(MapConstant.INITIAL_CAPACITY.getValue());
        map.put("userEntity.id", userId);
        map.put("menuEntity.parentId", parentId);
        return findAllByProperties(map);
    }

    /**
     * 根据userId和menuId查询
     * @param userId
     * @param menuId
     * @return
     */
    public UserMenuEntity findByMenuIdAndUserId(String userId, String menuId) {
        Map<String, Object> map = new HashMap<>(MapConstant.INITIAL_CAPACITY.getValue());
        map.put("userEntity.id", userId);
        map.put("menuEntity.id", menuId);
        return findByProperties(map);
    }

    /**
     * 根据userId删除
     * @param userId
     */
    public void deleteByUserId(String userId) {
        List<UserMenuEntity> userMenuEntityList = findByUserId(userId);
        deleteAll(userMenuEntityList);
    }

    /**
     * 修改
     * @param request
     */
    public void edit(RequestUserMenuDTO request) {
        String userId = request.getUserId();
        deleteByUserId(userId);
        addUserMenu(userId,request.getMenuIdList());
    }

    /**
     * 添加菜单
     * @param userId
     * @param menuIdList
     */
    public void addUserMenu(String userId, List<String> menuIdList) {
        if (CollectionUtils.isNotEmpty(menuIdList)) {
            UserEntity userEntity = userService.findById(userId);
            menuIdList.forEach(id->{
                UserMenuEntity userMenuEntity = new UserMenuEntity();
                MenuEntity menuEntity = menuService.findById(id);
                userMenuEntity.setMenuEntity(menuEntity);
                userMenuEntity.setUserEntity(userEntity);
                save(userMenuEntity);
            });
        }
    }

    /**
     * 验证用户页面权限
     */
    public void validateUserPermissions(String path) {
        UserEntity currentUser = UserThreadLocalUtils.get();
        MenuEntity menuEntity = menuService.findByPath(path);
        if (menuEntity == null) {
            throw new ServiceException("您要访问的路径不存在！");
        }
        UserMenuEntity userMenuEntity = findByMenuIdAndUserId(currentUser.getId(), menuEntity.getId());
        if (userMenuEntity == null) {
            throw new ServiceException("您暂时无权限访问！");
        }
    }

    /**
     * 获取系统所有菜单
     * @return
     */
    public List<ResponseUserMenuDTO> getAllMenus() {
        List<MenuEntity> menuList = menuService.findByParentIdAndOrderByMenuOrder(BaseEntityConstant.PARENT.getValue(),"");
        List<ResponseUserMenuDTO> userMenuDTOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuList)) {
            return userMenuDTOList;
        }
        for (MenuEntity entity : menuList) {
            ResponseUserMenuDTO responseUserMenuDTO = new ResponseUserMenuDTO();
            CopyBeanUtil.copy(entity,responseUserMenuDTO);
            userMenuDTOList.add(responseUserMenuDTO);
        }
        for (ResponseUserMenuDTO menu : userMenuDTOList) {
            menu.setChildren(getChild(menu.getId()));
        }
        return userMenuDTOList;
    }

    /**
     * 标记用户已有菜单
     * @param allMenuDTO
     * @param userId
     * @return
     */
    public List<ResponseUserMenuDTO> checkHasMenus(List<ResponseUserMenuDTO> allMenuDTO, String userId) {
        for (ResponseUserMenuDTO parent : allMenuDTO) {
            parent.setExpand(Boolean.TRUE);
            List<ResponseUserMenuDTO> children = parent.getChildren();
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            for (ResponseUserMenuDTO child : children) {
                UserMenuEntity userMenu = findByMenuIdAndUserId(userId, child.getId());
                if (userMenu != null) {
                    child.setChecked(Boolean.TRUE);
                }
            }
        }
        return allMenuDTO;
    }
    
    /**
     * 根据用户id查询所有菜单
     * @param userId
     * @return
     */
    public List<ResponseUserMenuDTO> getAllMenuByUserId(String userId) {
        List<UserMenuEntity> userAllMenu = findByUserIdAndParentId(userId, RootConstant.ROOT_ID.getValue());
        if (CollectionUtils.isEmpty(userAllMenu)) {
            throw new ServiceException("未配置菜单权限， 请联系管理员！");
        }

        List<UserMenuEntity> userMenuEntityList = findByUserId(userId);
        List<String> hasIdList = userMenuEntityList.stream().map(item->item.getMenuEntity().getId()).collect(Collectors.toList());

        List<ResponseUserMenuDTO> userAllMenuDTOList = new ArrayList<>();
        for (UserMenuEntity userMenuEntity : userAllMenu) {
            ResponseUserMenuDTO dto = new ResponseUserMenuDTO();
            MenuEntity menuEntity = userMenuEntity.getMenuEntity();
            CopyBeanUtil.copy(menuEntity, dto);
            userAllMenuDTOList.add(dto);
        }
        for (ResponseUserMenuDTO menu : userAllMenuDTOList) {
            List<ResponseUserMenuDTO> childDTOList = getChild(menu.getId());
            if (CollectionUtils.isNotEmpty(childDTOList)) {
                childDTOList = childDTOList.stream().filter(dto -> hasIdList.contains(dto.getId())).collect(Collectors.toList());
                menu.setChildren(childDTOList);
            }
        }
        return userAllMenuDTOList;
    }

    /**
     * 递归查询子菜单
     * @param menuId
     * @return
     */
    public List<ResponseUserMenuDTO> getChild(String menuId) {
        List<ResponseUserMenuDTO> childList = new ArrayList<>();
        List<MenuEntity> menuList = menuService.findByParentIdAndOrderByMenuOrder(menuId, "");
        if(CollectionUtils.isEmpty(menuList)){
            return childList;
        }
        for (MenuEntity entity : menuList) {
            ResponseUserMenuDTO responseUserMenuDTO = new ResponseUserMenuDTO();
            CopyBeanUtil.copy(entity, responseUserMenuDTO);
            childList.add(responseUserMenuDTO);
        }
        for (ResponseUserMenuDTO menuDTO : childList) {
            menuDTO.setChildren(getChild(menuDTO.getId()));
        }
        return childList;
    }
}
