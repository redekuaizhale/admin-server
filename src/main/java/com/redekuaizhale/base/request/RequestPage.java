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
package com.redekuaizhale.base.request;

import com.redekuaizhale.base.page.Page;
import com.redekuaizhale.base.param.OrderParam;
import com.redekuaizhale.base.param.QueryParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页请求
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Data
public class RequestPage extends Page {

    /**
     * 查询条件
     */
    private List<QueryParam> queryParamList = new ArrayList<>();

    /**
     * 排序条件
     */
    private List<OrderParam> orderParamList = new ArrayList<>();

}
