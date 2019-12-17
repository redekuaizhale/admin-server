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
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.menu.constant.MenuConstant;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.menu.service.MenuService;
import com.redekuaizhale.user.entity.UserEntity;
import com.redekuaizhale.usermenu.dto.ResponseUserAllMenuDTO;
import com.redekuaizhale.usermenu.entity.UserMenuEntity;
import com.redekuaizhale.usermenu.repository.UserMenuRepository;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import com.redekuaizhale.utils.threadlocal.UserThreadLocalUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void setRository(UserMenuRepository userMenuRepository) {
        super.baseRepository = userMenuRepository;
    }

    /**
     * 根据userId查询用户菜单
     * @param userId
     * @return
     */
    public List<UserMenuEntity> findByUserId(String userId) {
        return findAllByProperty("userEntity.id",userId);
    }

    public UserMenuEntity findByMenuIdAndUserId(String userId, String menuId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userEntity.id", userId);
        map.put("menuEntity.id", menuId);
        return findByProperties(map);
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
     * 根据用户id查询所有菜单
     * @param userId
     * @return
     */
    public List<ResponseUserAllMenuDTO> getAllMenuByUserId(String userId) {
        List<MenuEntity> userAllParentMenuList = new ArrayList<>();
        List<UserMenuEntity> userAllMenu = findByUserId(userId);

        if (CollectionUtils.isEmpty(userAllMenu)) {
            return null;
        }

        userAllMenu.forEach(item->{
            MenuEntity menuEntity = menuService.findById(item.getMenuEntity().getId());
            if (StringUtils.equals(menuEntity.getParentId(), MenuConstant.PARENT_MENU_FLAG.getKey())) {
                userAllParentMenuList.add(menuEntity);
            }
        });

        if (CollectionUtils.isEmpty(userAllParentMenuList)) {
            return null;
        }

        List<ResponseUserAllMenuDTO> userAllMenuDTOList = BeanCopyUtils.entityListToModelList(userAllParentMenuList, ResponseUserAllMenuDTO.class);

        if (CollectionUtils.isEmpty(userAllMenuDTOList)) {
            return null;
        }

        for (ResponseUserAllMenuDTO menu : userAllMenuDTOList) {
            menu.setChildren(getChild(menu.getId()));
        }
        return userAllMenuDTOList;
    }

    /**
     * 递归查询子菜单
     * @param menuId
     * @return
     */
    public List<ResponseUserAllMenuDTO> getChild(String menuId) {
        List<ResponseUserAllMenuDTO> childList = new ArrayList<>();
        List<MenuEntity> menuList = menuService.findByParentId(menuId, null);
        if(CollectionUtils.isEmpty(menuList)){
            return childList;
        }
        childList = BeanCopyUtils.entityListToModelList(menuList, ResponseUserAllMenuDTO.class);
        for (ResponseUserAllMenuDTO menuDTO : childList) {
            menuDTO.setChildren(getChild(menuDTO.getId()));
        }
        return childList;
    }
}
