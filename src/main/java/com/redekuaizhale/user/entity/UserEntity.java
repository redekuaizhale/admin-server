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
package com.redekuaizhale.user.entity;

import com.redekuaizhale.base.entity.BaseEntity;
import com.redekuaizhale.company.entity.CompanyEntity;
import com.redekuaizhale.dept.entity.DeptEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 用户Entity
 * @author redekuaizhale
 * @date 2019-05-28
 * @company Dingxuan
 */
@Data
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity {

    /**
     * 所属机构
     */
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    /**
     * 所属部门
     */
    @ManyToOne()
    @JoinColumn(name = "dept_id")
    private DeptEntity deptEntity;

    /**
     * 登录名
     */
    private String loginCode;

    /**
     * 密码
     */
    private String password;

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
