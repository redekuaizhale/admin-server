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
package com.redekuaizhale.company.dto;

import com.redekuaizhale.base.dto.BaseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghui
 * @date 2019-12-23
 * @company Dingxuan
 */
@Data
public class CompanyDTO extends BaseDTO {

    /**
     * 父id
     */
    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 状态
     */
    private String status;

    /**
     * 路径
     */
    private String path;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 排序字段
     */
    private Integer companyOrder;

    /**
     * 子机构
     */
    private List<CompanyDTO> children = new ArrayList<>();

}
