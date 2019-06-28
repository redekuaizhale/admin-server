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
package com.redekuaizhale.controller;

import com.redekuaizhale.base.response.Result;
import com.redekuaizhale.dto.user.RequestLoginUserDTO;
import com.redekuaizhale.entity.User;
import com.redekuaizhale.service.UserMenuService;
import com.redekuaizhale.service.UserService;
import com.redekuaizhale.utils.user.UserThreadLocalUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody @Validated RequestLoginUserDTO request){
        return Result.newSuccessResult("登录成功！",
                userService.getLoginUser(request));
    }

    @GetMapping("menus.do")
    @ApiOperation("获取用户所有菜单")
    public Result menus(){
        User currentUser = UserThreadLocalUtils.get();
        return Result.newSuccessResult("获取用户菜单成功！",
                userMenuService.queryAllMenuByUserId(currentUser.getId()));
    }
}
