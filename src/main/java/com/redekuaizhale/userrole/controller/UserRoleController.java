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
package com.redekuaizhale.userrole.controller;

import com.redekuaizhale.base.request.RequestPage;
import com.redekuaizhale.base.response.ResponsePage;
import com.redekuaizhale.base.response.Result;
import com.redekuaizhale.constants.CRUDConstant;
import com.redekuaizhale.dept.dto.RequestDeptDTO;
import com.redekuaizhale.dept.dto.ResponseDeptDTO;
import com.redekuaizhale.dept.entity.DeptEntity;
import com.redekuaizhale.dept.service.DeptService;
import com.redekuaizhale.userrole.dto.RequestUserRoleDTO;
import com.redekuaizhale.userrole.dto.ResponseUserRoleDTO;
import com.redekuaizhale.userrole.entity.UserRoleEntity;
import com.redekuaizhale.userrole.service.UserRoleService;
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
        List<ResponseUserRoleDTO> list = BeanCopyUtils.entityListToDTOList(resultList, ResponseUserRoleDTO.class);
        result.setResultList(list);
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
}
