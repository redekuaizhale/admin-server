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

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.redekuaizhale.constants.DirecttionConstant;
import com.redekuaizhale.utils.date.DateUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.*;

/**
 * 查询参数
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Data
public class QueryParam {

    /**
     * 查询字段
     */
    private String fieldName;

    /**
     * 字段值
     */
    private String fieldValue;

    /**
     * 操作符 如 like,=,between
     */
    private String operate;

    /**
     * 字段数据类型
     */
    private String fieldValueClass;

    public static List<QueryParam> newQueryParams(Map<String, Object> properties) {
        List<QueryParam> list = new ArrayList<>();
        Set<Map.Entry<String, Object>> entries = properties.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String property = entry.getKey();
            Object propertyValue = entry.getValue();
            QueryParam queryParam = null;
            if (propertyValue == null) {
                queryParam = newQueryParam(property, "is", propertyValue);
            } else {
                queryParam = newQueryParam(property, "=", propertyValue);
            }
            list.add(queryParam);
        }
        return list;
    }

    public static QueryParam newQueryParam(String property, String operate, Object value) {
        QueryParam queryParam = new QueryParam();
        queryParam.setOperate(operate);
        queryParam.setFieldName(property);
        if (value instanceof String) {
            queryParam.setFieldValue(value.toString());
            queryParam.setFieldValueClass("String");
        } else if (value instanceof Integer) {
            queryParam.setFieldValueClass(value.toString());
            queryParam.setFieldValueClass("Integer");
        } else if (value instanceof Long) {
            queryParam.setFieldValueClass(value.toString());
            queryParam.setFieldValueClass("Long");
        } else if (value instanceof Boolean) {
            queryParam.setFieldValueClass(value.toString());
            queryParam.setFieldValueClass("Boolean");
        } else if (value instanceof Date) {
            queryParam.setFieldValueClass(DateUtils.dateToString((Date) value));
            queryParam.setFieldValueClass("DateTime");
        } else if (value instanceof BigDecimal) {
            queryParam.setFieldValueClass(value.toString());
            queryParam.setFieldValueClass("BigDecimal");
        } else if (value instanceof List) {
            queryParam.setFieldValueClass(JSON.toJSONString(value));
            queryParam.setFieldValueClass("List");
        }
        return queryParam;
    }

    public static void setQueryParams(Query query, List<QueryParam> queryParams) {
        for (QueryParam queryParam : queryParams) {
            setQueryParam(query, queryParam);
        }
    }

    public static void setQueryParam(Query query, QueryParam queryParam) {
        String paramName = queryParam.getFieldName();
        String replace = paramName.replace(".", "");
        String paramValueClass = queryParam.getFieldValueClass();
        Object targetParamValue = null;
        String originalParamValue = queryParam.getFieldValue();
        String operate = queryParam.getOperate();
        if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.STRING.getKey())) {
            if (StringUtils.equalsIgnoreCase(operate, DirecttionConstant.LIKE.getKey())) {
                targetParamValue = "%" + originalParamValue + "%";
            } else if (StringUtils.equalsIgnoreCase(operate, DirecttionConstant.STARTWITH.getKey())) {
                targetParamValue = originalParamValue + "%";
            } else if (StringUtils.equalsIgnoreCase(operate, DirecttionConstant.ENDWITH.getKey())) {
                targetParamValue = "%" + originalParamValue;
            } else {
                targetParamValue = originalParamValue;
            }
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.INTEGER.getKey())) {
            targetParamValue = Integer.parseInt(originalParamValue);
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.LONG.getKey())) {
            targetParamValue = Long.parseLong(originalParamValue);
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.BOOLEAN.getKey())) {
            targetParamValue = Lists.newArrayList("1", "true", "True").contains(originalParamValue);
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.DATETIME.getKey())) {
            if (StringUtils.equalsIgnoreCase(operate, DirecttionConstant.BETWEEN.getKey())) {
                String str = originalParamValue;
                String[] split = StringUtils.split(str, "|");
                query.setParameter(replace + "1", DateUtils.stringToDate(split[0]));
                query.setParameter(replace + "2", DateUtils.stringToDate(split[1]));
                return;
            } else {
                targetParamValue = DateUtils.stringToDate(originalParamValue);
            }
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.DATE.getKey())) {
            if (StringUtils.equalsIgnoreCase(operate, DirecttionConstant.BETWEEN.getKey())) {
                String str = originalParamValue;
                String[] split = StringUtils.split(str, "|");
                query.setParameter(replace + "1", DateUtils.stringToDate(split[0], DateUtils.YYYY_MM_DD));
                query.setParameter(replace + "2", DateUtils.stringToDate(split[1], DateUtils.YYYY_MM_DD));
                return;
            } else {
                targetParamValue = DateUtils.stringToDate(originalParamValue, DateUtils.YYYY_MM_DD);
            }
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.BIGDECIMAL.getKey())) {
            targetParamValue = new BigDecimal(originalParamValue);
        } else if (StringUtils.equalsIgnoreCase(paramValueClass, DirecttionConstant.LIST.getKey())) {
            targetParamValue = (List) JSON.parse(originalParamValue);
        }
        query.setParameter(replace, targetParamValue);
    }
}
