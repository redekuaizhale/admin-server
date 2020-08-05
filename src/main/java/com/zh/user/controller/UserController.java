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
package com.zh.user.controller;

import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.base.response.Result;
import com.zh.constants.CRUDConstant;
import com.zh.user.dto.RequestLoginUserDTO;
import com.zh.user.dto.RequestUserDTO;
import com.zh.user.dto.ResponseUserDTO;
import com.zh.user.entity.UserEntity;
import com.zh.user.service.UserService;
import com.zh.usermenu.service.UserMenuService;
import com.zh.utils.bean.BeanCopyUtils;
import com.zh.utils.threadlocal.UserThreadLocalUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@RestController
@RequestMapping("/user/")
@Api("用户相关api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMenuService userMenuService;

    @PostMapping("login.do")
    @ApiOperation("登录")
    public Result login(@RequestBody @Validated RequestLoginUserDTO request) {
        return Result.newSuccessResult("登录成功！", userService.getLoginUser(request));
    }

    @PostMapping("userMenus.do")
    @ApiOperation("获取用户所有菜单")
    public Result userMenus() {
        UserEntity currentUser = UserThreadLocalUtils.get();
        return Result.newSuccessResult("获取用户菜单成功！", userMenuService.getAllMenuByUserId(currentUser.getId()));
    }

    @GetMapping("userMenuPermission.do")
    @ApiOperation("验证用户访问权限")
    public Result userMenuPermission(@RequestParam String path) {
        userMenuService.validateUserPermissions(path);
        return Result.newSuccessResult("验证权限通过！");
    }

    @PostMapping("query.do")
    @ApiOperation("用户查询")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = userService.query(requestPage);
        List<UserEntity> resultList = (List<UserEntity>) result.getResultList();
        List<ResponseUserDTO> list = BeanCopyUtils.entityListToDTOList(resultList, ResponseUserDTO.class);
        result.setResultList(list);
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("add.do")
    @ApiOperation("添加用户")
    public Result add(@RequestBody RequestUserDTO request) {
        String id = userService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(),id);
    }

    @PostMapping("edit.do")
    @ApiOperation("修改用户")
    public Result edit(@RequestBody RequestUserDTO request) {
        userService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }

    @PostMapping("delete.do")
    @ApiOperation("删除用户")
    public Result delete(@RequestBody RequestUserDTO request) {
        userService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }
}
