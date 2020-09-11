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
package com.zh.parameter.entity;

import com.zh.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhanghui
 * @date 2020-09-09
 * @company Dingxuan
 */
@Data
@Entity
@Table(name = "t_parameter")
public class ParameterEntity extends BaseEntity {

    /**
     * 参数key
     */
    private String parameterKey;

    /**
     * 参数1
     */
    private String value;

    /**
     * 参数2
     */
    private String value2;

    /**
     * 参数3
     */
    private String value3;

    /**
     * 参数4
     */
    private String value4;

    /**
     * 参数5
     */
    private String value5;

}

