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
package com.redekuaizhale.service;

import com.redekuaizhale.base.exception.ServiceException;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.constants.DirecttionConstant;
import com.redekuaizhale.dto.usermenu.ResponseUserAllMenuDTO;
import com.redekuaizhale.entity.Menu;
import com.redekuaizhale.entity.UserMenu;
import com.redekuaizhale.repository.UserMenuRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
public class UserMenuService extends BaseService<UserMenu> {

    @Autowired
    private MenuService menuService;

    @Autowired
    public void setRository(UserMenuRepository userMenuRepository) {
        super.baseRepository = userMenuRepository;
    }

    /**
     * 根据用户id查询所有菜单
     * @param userId
     * @return
     */
    public List<ResponseUserAllMenuDTO> queryAllMenuByUserId(String userId){
        List<UserMenu> userHasMenuList = findAllByProperty("user.id", userId);
        if (CollectionUtils.isEmpty(userHasMenuList)) {
            throw new ServiceException(String.format("用户id | %s:","未配置菜单信息！",userId));
        }
        List<String> userHasMenuIdList = new ArrayList<>();
        userHasMenuList.forEach(item ->  userHasMenuIdList.add(item.getMenu().getId()));
        return getUserAllMenuList(userHasMenuIdList);
    }


    /**
     * 设置用户菜单响应属性值
     * @param menu
     * @return
     */
    public ResponseUserAllMenuDTO setResponseUserInfo(Menu menu,ResponseUserAllMenuDTO menuDTO){
        menuDTO = menuDTO == null ? new ResponseUserAllMenuDTO() : menuDTO;
        BeanUtils.copyProperties(menu,menuDTO);
        return menuDTO;
    }

    /**
     * 获取用户所有菜单信息
     * @param userHasMenuIdList
     * @return
     */
    public List<ResponseUserAllMenuDTO> getUserAllMenuList(List<String> userHasMenuIdList) {
        List<ResponseUserAllMenuDTO> userAllMenuList = new ArrayList<>();
        List<Menu> parentMenuList = menuService.findByParentId("", DirecttionConstant.ASC.getCode());
        parentMenuList.forEach(parent->{
            if (!userHasMenuIdList.contains(parent.getId())) {
                return;
            }
            ResponseUserAllMenuDTO parentMenu = new ResponseUserAllMenuDTO();
            List<Menu> childMenuList = menuService.findByParentId(parent.getId(),DirecttionConstant.ASC.getCode());
            List<ResponseUserAllMenuDTO> userMenuList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(childMenuList)) {
                childMenuList.forEach(child-> userMenuList.add(setResponseUserInfo(child,null)));
            }
            parentMenu.setChildren(userMenuList);
            userAllMenuList.add(setResponseUserInfo(parent,parentMenu));
        });
        return userAllMenuList;
    }
}
