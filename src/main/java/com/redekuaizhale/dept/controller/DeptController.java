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
package com.redekuaizhale.dept.controller;

import com.redekuaizhale.base.request.RequestPage;
import com.redekuaizhale.base.response.ResponsePage;
import com.redekuaizhale.base.response.Result;
import com.redekuaizhale.constants.CRUDConstant;
import com.redekuaizhale.dept.dto.RequestDeptDTO;
import com.redekuaizhale.dept.dto.ResponseDeptDTO;
import com.redekuaizhale.dept.entity.DeptEntity;
import com.redekuaizhale.dept.service.DeptService;
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
@RequestMapping("/dept/")
@Api("部门相关api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("query.do")
    @ApiOperation("部门查询")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = deptService.query(requestPage);
        List<DeptEntity> resultList = (List<DeptEntity>) result.getResultList();
        List<ResponseDeptDTO> list = BeanCopyUtils.entityListToDTOList(resultList, ResponseDeptDTO.class);
        result.setResultList(list);
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("add.do")
    @ApiOperation("添加部门")
    public Result add(@RequestBody RequestDeptDTO request) {
        String id = deptService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(),id);
    }

    @PostMapping("edit.do")
    @ApiOperation("修改部门")
    public Result edit(@RequestBody RequestDeptDTO request) {
        deptService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }

    @PostMapping("delete.do")
    @ApiOperation("删除部门")
    public Result delete(@RequestBody RequestDeptDTO request) {
        deptService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }

    @PostMapping("findByCompanyId.do")
    @ApiOperation("根据机构查询")
    public Result findByCompanyId(@RequestBody RequestDeptDTO request) {
        List<DeptEntity> deptEntityList = deptService.findByCompanyId(request);
        List<ResponseDeptDTO> responseDeptDTOList = BeanCopyUtils.entityListToDTOList(deptEntityList, ResponseDeptDTO.class);
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue(),responseDeptDTOList);
    }
}
