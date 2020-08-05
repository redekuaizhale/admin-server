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
package com.zh.userrole.dto;

import com.zh.base.dto.BaseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author zhanghui
 * @date 2020-05-27
 * @company Dingxuan
 */
@Data
public class UserRoleDTO extends BaseDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色id列表
     */
    private List<String> roleIdList;
}
