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
package com.zh.menu.controller;

import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.base.response.Result;
import com.zh.constants.CRUDConstant;
import com.zh.menu.dto.RequestMenuDTO;
import com.zh.menu.dto.ResponseMenuDTO;
import com.zh.menu.entity.MenuEntity;
import com.zh.menu.service.MenuService;
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
        result.setResultList(ResponseMenuDTO.toDTOList(resultList));
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
