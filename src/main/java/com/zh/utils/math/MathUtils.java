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
package com.zh.utils.math;

import java.math.BigDecimal;

import static cn.hutool.core.util.NumberUtil.round;

/**
 * @author zhanghui
 * @date 2020-08-12
 * @company Dingxuan
 */
public class MathUtils {

    private static final int SCALE = 2;

    /**
     * 四舍五入,保留两位小数
     *
     * @param a
     * @return
     */
    public static BigDecimal roundCustom(BigDecimal a, int... scale) {
        return round(a, scale.length == 0 ? SCALE : scale[0]);
    }
}
