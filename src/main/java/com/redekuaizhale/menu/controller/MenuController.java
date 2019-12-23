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
package com.redekuaizhale.menu.controller;

import com.redekuaizhale.base.request.RequestPage;
import com.redekuaizhale.base.response.ResponsePage;
import com.redekuaizhale.base.response.Result;
import com.redekuaizhale.constants.CRUDConstant;
import com.redekuaizhale.menu.dto.ResponseMenuDTO;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.menu.service.MenuService;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghui
 * @date 2019-12-10
 * @company Dingxuan
 */
@RestController
@RequestMapping("/menu/")
@Api("菜单相关api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("query.do")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage query = menuService.query(requestPage);
        List<MenuEntity> resultList = (List<MenuEntity>) query.getResultList();
        List<ResponseMenuDTO> list = BeanCopyUtils.entityListToModelList(resultList, ResponseMenuDTO.class);
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), list);
    }
}
