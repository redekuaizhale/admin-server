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
package com.redekuaizhale.constants;

/**
 * entity常量
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
public enum BaseEntityConstant {

    /**
     * 可用
     */
    ENABLE("0"),

    /**
     * 已删除
     */
    DELETE("1");

    String key;

    BaseEntityConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}