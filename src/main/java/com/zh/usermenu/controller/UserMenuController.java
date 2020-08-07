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
package com.zh.usermenu.controller;

import com.zh.base.response.Result;
import com.zh.constants.CRUDConstant;
import com.zh.usermenu.dto.RequestUserMenuDTO;
import com.zh.usermenu.dto.ResponseUserMenuDTO;
import com.zh.usermenu.service.UserMenuService;
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
@RequestMapping("/userMenu/")
@Api("用户菜单相关api")
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @PostMapping("userHasMenus.do")
    @ApiOperation("获取用户已有菜单")
    public Result userHasMenus(@RequestBody RequestUserMenuDTO request) {
        List<ResponseUserMenuDTO> allMenus = userMenuService.getAllMenus();
        userMenuService.checkHasMenus(allMenus,request.getUserId());
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(),allMenus);
    }

    @PostMapping("editUserHasMenus.do")
    @ApiOperation("修改用户已有菜单")
    public Result editUserHasMenus(@RequestBody RequestUserMenuDTO request) {
        userMenuService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }
}
