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
package com.zh.dept.entity;

import com.zh.base.entity.BaseEntity;
import com.zh.company.entity.CompanyEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhanghui
 * @date 2020-05-27
 * @company Dingxuan
 */
@Data
@Entity
@Table(name = "t_dept")
public class DeptEntity extends BaseEntity {


    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序字段
     */
    private Integer deptOrder;

    /**
     * 描述
     */
    private String remark;

    /**
     * 成立时间
     */
    private Date setupDate;


}
