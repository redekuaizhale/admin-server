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
package com.zh.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录请求DTO
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Data
@ApiModel(value = "登录字段")
public class RequestLoginUserDTO {

    @NotEmpty(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名", name = "loginCode", required = true)
    private String loginCode;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;

}
