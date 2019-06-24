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

import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.entity.Menu;
import com.redekuaizhale.repository.MenuRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    public void setRository(MenuRepository menuRepository) {
        super.baseRepository = menuRepository;
    }

    /**
     * 根据level查询菜单
     * @param level
     * @return
     */
    public Menu findByLevel(String level) {
        return findByProperty("lavel", level);
    }

    /**
     * 根据父id查询菜单，并根据 menu_order 字段升序
     * @param parentId
     * @return
     */
    public List<Menu> findByParentId(String parentId,String order){
        OrderParam orderParam = new OrderParam();
        orderParam.setProperty("menuOrder");
        orderParam.setDirecttion(StringUtils.isEmpty(order) ? "asc" : order);
        return findAllByProperty("parentId", parentId, orderParam);
    }
}
