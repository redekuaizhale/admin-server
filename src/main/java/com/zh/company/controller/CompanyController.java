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
package com.zh.company.controller;

import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.base.response.Result;
import com.zh.company.dto.CompanyDTO;
import com.zh.company.dto.RequestCompanyDTO;
import com.zh.company.dto.ResponseCompanyDTO;
import com.zh.company.entity.CompanyEntity;
import com.zh.company.service.CompanyService;
import com.zh.constants.CRUDConstant;
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
@RequestMapping("/company/")
@Api("机构相关api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("query.do")
    @ApiOperation("机构查询")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = companyService.query(requestPage);
        List<CompanyEntity> resultList = (List<CompanyEntity>) result.getResultList();
        result.setResultList(ResponseCompanyDTO.toDTOList(resultList));
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("add.do")
    @ApiOperation("添加机构")
    public Result add(@RequestBody RequestCompanyDTO request) {
        String id = companyService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(), id);
    }

    @PostMapping("edit.do")
    @ApiOperation("修改机构")
    public Result edit(@RequestBody RequestCompanyDTO request) {
        companyService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }

    @PostMapping("delete.do")
    @ApiOperation("删除机构")
    public Result delete(@RequestBody RequestCompanyDTO request) {
        companyService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }

    @PostMapping("findCompanyTree.do")
    @ApiOperation("查询机构树")
    public Result findCompanyTree(@RequestBody RequestCompanyDTO request) {
        CompanyDTO resultData = companyService.findCompanyTree();
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue(), resultData);
    }

    @PostMapping("findLoginUserCompanyTree.do")
    @ApiOperation("查询当前登录人机构树")
    public Result findLoginUserCompanyTree(@RequestBody RequestCompanyDTO request) {
        List<CompanyDTO> resultData = companyService.findLoginUserCompanyTree();
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue(), resultData);
    }
}
