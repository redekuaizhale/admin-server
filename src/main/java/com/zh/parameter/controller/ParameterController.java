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
package com.zh.parameter.controller;

import com.zh.base.request.RequestPage;
import com.zh.base.response.ResponsePage;
import com.zh.base.response.Result;
import com.zh.constants.CRUDConstant;
import com.zh.parameter.dto.RequestParameterDTO;
import com.zh.parameter.dto.ResponseParameterDTO;
import com.zh.parameter.entity.ParameterEntity;
import com.zh.parameter.service.ParameterService;
import com.zh.utils.bean.CopyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghui
 * @date 2020-09-09
 * @company Dingxuan
 */
@RestController
@RequestMapping("/parameter/")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @PostMapping("query.do")
    public Result query(@RequestBody RequestPage requestPage) {
        ResponsePage result = parameterService.query(requestPage);
        List<ParameterEntity> resultList = (List<ParameterEntity>) result.getResultList();
        result.setResultList(ResponseParameterDTO.toDTOList(resultList));
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), result);
    }

    @PostMapping("findByKey.do")
    public Result findByKey(@RequestBody RequestParameterDTO request) {
        ParameterEntity entity = parameterService.findByKey(request);
        ResponseParameterDTO dto = new ResponseParameterDTO();
        CopyBeanUtil.entityToDTO(entity,dto);
        return Result.newSuccessResult(CRUDConstant.QUERY.getValue(), dto);
    }

    @PostMapping("add.do")
    public Result add(@RequestBody RequestParameterDTO request) {
        String id = parameterService.add(request);
        return Result.newSuccessResult(CRUDConstant.ADD.getValue(),id);
    }

    @PostMapping("edit.do")
    public Result edit(@RequestBody RequestParameterDTO request) {
        parameterService.edit(request);
        return Result.newSuccessResult(CRUDConstant.UPDATE.getValue());
    }

    @PostMapping("delete.do")
    public Result delete(@RequestBody RequestParameterDTO request) {
        parameterService.deleteById(request.getId());
        return Result.newSuccessResult(CRUDConstant.DELETE.getValue());
    }
}
