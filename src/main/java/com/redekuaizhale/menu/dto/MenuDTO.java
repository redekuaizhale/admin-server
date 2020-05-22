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
package com.redekuaizhale.menu.dto;

import com.redekuaizhale.base.dto.BaseDTO;
import lombok.Data;

/**
 * @author zhanghui
 * @date 2019-12-23
 * @company Dingxuan
 */
@Data
public class MenuDTO extends BaseDTO {
    /**
     * 父id
     */
    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 是否隐藏
     */
    private String hidden;

    /**
     * 菜单排序
     */
    private Integer menuOrder;

    /**
     * 是否可用
     */
    private String useFlag;
}
