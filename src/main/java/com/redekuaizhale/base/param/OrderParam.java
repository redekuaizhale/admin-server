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
package com.redekuaizhale.base.param;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 排序参数
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Data
public class OrderParam {

    /**
     * 排序字段
     */
    private String property;

    /**
     * asc or desc
     */
    private String direction;

    public static List<OrderParam> newOrderParams(OrderParam... orderParams) {
        if (orderParams == null) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(orderParams);
    }

    public static OrderParam newOrderParam(String property, String direction) {
        OrderParam orderParam = new OrderParam();
        orderParam.setProperty(property);
        orderParam.setDirection(direction);
        return orderParam;
    }
}
