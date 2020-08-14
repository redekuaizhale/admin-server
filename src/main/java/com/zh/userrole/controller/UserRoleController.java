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
package com.zh.userrole.controller;

import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.base.response.Result;
import com.zh.constants.CRUDConstant;
import com.zh.userrole.dto.RequestUserRoleDTO;
import com.zh.userrole.dto.ResponseUserRoleDTO;
import com.zh.userrole.entity.UserRoleEntity;
import com.zh.userrole.service.UserRoleService;
import com.zh.utils.bean.CopyBeanUtil;
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
@RequestMapping("/userRole/")
@Api("用户角色相关api")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("query.do")
    @ApiOperation("用户角色查询")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = userRoleService.query(requestPage);
        List<UserRoleEntity> resultList = (List<UserRoleEntity>) result.getResultList();
        result.setResultList(ResponseUserRoleDTO.toDTOList(resultList));
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("add.do")
    @ApiOperation("添加用户角色")
    public Result add(@RequestBody RequestUserRoleDTO request) {
        String id = userRoleService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(),id);
    }

    @PostMapping("delete.do")
    @ApiOperation("删除用户角色")
    public Result delete(@RequestBody RequestUserRoleDTO request) {
        userRoleService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }

    @PostMapping("deleteByUserIdAndRoleId.do")
    @ApiOperation("删除用户角色")
    public Result deleteByUserIdAndRoleId(@RequestBody RequestUserRoleDTO request) {
        userRoleService.deleteByUserIdAndRoleId(request);
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }

    @PostMapping("addUserRole.do")
    @ApiOperation("添加用户角色")
    public Result addUserRole(@RequestBody RequestUserRoleDTO request) {
        userRoleService.addUserRole(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue());
    }

    @PostMapping("findHasRoleIds.do")
    @ApiOperation("添加用户角色")
    public Result findHasRoleIds(@RequestBody RequestUserRoleDTO request) {
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), userRoleService.findRoleIdsByUserId(request));
    }
}
