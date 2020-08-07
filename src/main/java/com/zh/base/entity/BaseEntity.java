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
package com.zh.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础Entity
 * @author redekuaizhale
 * @date 2019-05-29
 * @company Dingxuan
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     * 主键id
     */
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "uuid")
    @Id
    private String id;

    /**
     * 版本号
     */
    @Version
    private Integer version;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新人id
     */
    private String updateUserId;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 删除标识
     */
    private String delFlag;
}
