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
package com.redekuaizhale.menu.service;

import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.menu.repository.MenuRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单Service
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class MenuService extends BaseService<MenuEntity> {

    @Autowired
    public void setRository(MenuRepository menuRepository) {
        super.baseRepository = menuRepository;
    }

    /**
     * 根据父id查询菜单，并根据 menu_order 字段升序
     * @param parentId
     * @return
     */
    public List<MenuEntity> findByParentId(String parentId, String order) {
        OrderParam orderParam = new OrderParam();
        orderParam.setProperty("menuOrder");
        orderParam.setDirection(StringUtils.isEmpty(order) ? "asc" : order);
        return findAllByProperty("parentId", parentId, orderParam);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public MenuEntity findByName(String name) {
       return findByProperty("name", name);
    }

    /**
     * 根据path查询
     * @param name
     * @return
     */
    public MenuEntity findByPath(String path) {
        return findByProperty("path", path);
    }
}
