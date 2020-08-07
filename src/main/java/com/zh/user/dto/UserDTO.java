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

import com.zh.base.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhanghui
 * @date 2020-05-26
 * @company Dingxuan
 */
@Data
public class UserDTO extends BaseDTO {

    /**
     * 机构id
     */
    private String companyId;

    /**
     * 机构简称
     */
    private String companyName;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 登录名
     */
    private String loginCode;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态（可用,停用）
     */
    private String status;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 备注
     */
    private String remark;
}
