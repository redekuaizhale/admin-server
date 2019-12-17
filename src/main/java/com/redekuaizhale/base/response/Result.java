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
package com.redekuaizhale.base.response;

import com.redekuaizhale.constants.ResultConstant;
import lombok.Data;

/**
 * 返回结果类
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Data
public class Result {

    /**
     * 返回码
     */
    private String resultCode;

    /**
     * 返回提示
     */
    private String resultMessage;

    /**
     * 返回数据
     */
    private Object data;


    /**
     * 成功
     * @param resultMessage
     * @return
     */
    public static Result newSuccessResult(String resultMessage) {
        Result result = new Result();
        result.setResultCode(ResultConstant.SUCCESS_CODE.getKey());
        result.setResultMessage(resultMessage);
        return result;
    }

    /**
     * 成功
     * @param resultMessage
     * @param data
     * @return
     */
    public static Result newSuccessResult(String resultMessage, Object data) {
        Result result = new Result();
        result.setResultCode(ResultConstant.SUCCESS_CODE.getKey());
        result.setResultMessage(resultMessage);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     * @param resultMessage
     * @return
     */
    public static Result newErrorResult(String resultMessage) {
        Result result = new Result();
        result.setResultCode(ResultConstant.ERROR_CODE.getKey());
        result.setResultMessage(resultMessage);
        return result;
    }
}
