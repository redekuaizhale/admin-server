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
import com.redekuaizhale.menu.dto.RequestMenuDTO;
import com.redekuaizhale.menu.dto.ResponseMenuDTO;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.menu.service.MenuService;
import com.redekuaizhale.utils.bean.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("菜单查询")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = menuService.query(requestPage);
        List<MenuEntity> resultList = (List<MenuEntity>) result.getResultList();
        List<ResponseMenuDTO> list = BeanCopyUtils.entityListToDTOList(resultList, ResponseMenuDTO.class);
        result.setResultList(list);
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("add.do")
    @ApiOperation("添加菜单")
    public Result add(@RequestBody RequestMenuDTO request) {
        String id = menuService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(),id);
    }

    @PostMapping("edit.do")
    @ApiOperation("修改菜单")
    public Result edit(@RequestBody RequestMenuDTO request) {
        menuService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }

    @PostMapping("delete.do")
    @ApiOperation("删除菜单")
    public Result delete(@RequestBody RequestMenuDTO request) {
        menuService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }
}
