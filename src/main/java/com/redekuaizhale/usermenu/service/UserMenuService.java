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
package com.redekuaizhale.usermenu.service;

import com.redekuaizhale.base.exception.ServiceException;
import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.constants.BaseEntityConstant;
import com.redekuaizhale.constants.DirecttionConstant;
import com.redekuaizhale.menu.constant.MenuConstant;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.menu.service.MenuService;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.user.service.UserService;
import com.redekuaizhale.usermenu.dto.RequestUserAllMenuDTO;
import com.redekuaizhale.usermenu.dto.ResponseUserAllMenuDTO;
import com.redekuaizhale.usermenu.entity.UserMenuEntity;
import com.redekuaizhale.usermenu.repository.UserMenuRepository;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import com.redekuaizhale.utils.threadlocal.UserThreadLocalUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class UserMenuService extends BaseService<UserMenuEntity> {

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
        return findAllByProperty("userEntity.id",userId, OrderParam.newOrderParam("menuEntity.menuOrder", DirecttionConstant.ASC.getValue()));
    }

    /**
     * 根据userId和menuId查询
     * @param userId
     * @param menuId
     * @return
     */
    public UserMenuEntity findByMenuIdAndUserId(String userId, String menuId) {
        Map<String, Object> map = new HashMap<>(2);
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
    public void edit(RequestUserAllMenuDTO request) {
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
    public List<ResponseUserAllMenuDTO> getAllMenus() {
        List<MenuEntity> allMenuList = menuService.findByParentId(BaseEntityConstant.PARENT.getValue(), null);
        List<ResponseUserAllMenuDTO> allMenuListDTO = BeanCopyUtils.entityListToDTOList(allMenuList, ResponseUserAllMenuDTO.class);

        if (CollectionUtils.isEmpty(allMenuListDTO)) {
            return null;
        }
        for (ResponseUserAllMenuDTO menu : allMenuListDTO) {
            menu.setChildren(getChild(menu.getId()));
        }
        return allMenuListDTO;
    }

    /**
     * 标记用户已有菜单
     * @param allMenuDTO
     * @param userId
     * @return
     */
    public List<ResponseUserAllMenuDTO> checkHasMenus(List<ResponseUserAllMenuDTO> allMenuDTO,String userId) {
        for (ResponseUserAllMenuDTO parent : allMenuDTO) {
            parent.setExpand(Boolean.TRUE);
            List<ResponseUserAllMenuDTO> children = parent.getChildren();
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            for (ResponseUserAllMenuDTO child : children) {
                UserMenuEntity userMenu = findByMenuIdAndUserId(userId, child.getId());
                if (userMenu != null) {
                    child.setChecked(Boolean.TRUE);
                }
                child.setExpand(Boolean.TRUE);
            }
        }
        return allMenuDTO;
    }
    
    /**
     * 根据用户id查询所有菜单
     * @param userId
     * @return
     */
    public List<ResponseUserAllMenuDTO> getAllMenuByUserId(String userId) {
        List<MenuEntity> userAllParentMenuList = new ArrayList<>();
        List<UserMenuEntity> userAllMenu = findByUserId(userId);

        if (CollectionUtils.isEmpty(userAllMenu)) {
            throw new ServiceException("未配置菜单权限， 请联系管理员！");
        }

        List<String> hasIdList = userAllMenu.stream().map(item->item.getMenuEntity().getId()).collect(Collectors.toList());

        userAllMenu.forEach(item->{
            MenuEntity menuEntity = menuService.findById(item.getMenuEntity().getId());
            if (StringUtils.equals(menuEntity.getParentId(), MenuConstant.PARENT_MENU_FLAG.getValue())) {
                userAllParentMenuList.add(menuEntity);
            }
        });

        List<ResponseUserAllMenuDTO> userAllMenuDTOList = BeanCopyUtils.entityListToDTOList(userAllParentMenuList, ResponseUserAllMenuDTO.class);

        for (ResponseUserAllMenuDTO menu : userAllMenuDTOList) {
            menu.setChildren(getChild(menu.getId(), hasIdList.toString()));
        }
        return userAllMenuDTOList;
    }

    /**
     * 递归查询子菜单
     * @param menuId
     * @return
     */
    public List<ResponseUserAllMenuDTO> getChild(String menuId,String... hasIdList) {
        List<ResponseUserAllMenuDTO> childList = new ArrayList<>();
        List<MenuEntity> menuList = menuService.findByParentId(menuId, null);
        if(CollectionUtils.isEmpty(menuList)){
            return childList;
        }
        if (hasIdList != null && hasIdList.length > 0) {
            menuList = menuList.stream().filter(item -> Arrays.toString(hasIdList).contains(item.getId())).collect(Collectors.toList());
        }
        childList = BeanCopyUtils.entityListToDTOList(menuList, ResponseUserAllMenuDTO.class);
        for (ResponseUserAllMenuDTO menuDTO : childList) {
            menuDTO.setChildren(getChild(menuDTO.getId(),hasIdList));
        }
        return childList;
    }
}
