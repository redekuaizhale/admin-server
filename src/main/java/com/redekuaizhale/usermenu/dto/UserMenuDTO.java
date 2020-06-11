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
package com.redekuaizhale.usermenu.dto;

import com.redekuaizhale.base.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @author zhanghui
 * @date 2020-05-28
 * @company Dingxuan
 */
@Data
public class UserMenuDTO extends BaseDTO {

    private String userId;

    private String title;

    private String path;

    private String name;

    private String parentName;

    private String parentId;

    private String redirect;

    private String component;

    private Boolean hidden;

    private String icon;

    private Boolean checked;

    private Boolean expand;

    private List<ResponseUserAllMenuDTO> children;
}
