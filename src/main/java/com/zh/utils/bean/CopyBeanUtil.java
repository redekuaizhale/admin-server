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
package com.zh.utils.bean;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对象copy工具类
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Slf4j
public class CopyBeanUtil {

    /**
     * entity转dto
     * @param entity
     * @param modelClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> T entityToDTO(F entity, Class<T> modelClass) {
        Object model = null;
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtil.copyProperties(entity, modelClass);
        return (T) model;
    }

    /**
     * 两个对象copy
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanUtil.copyProperties(source, target);
    }

    /**
     * entity转dto
     * @param source
     * @param target
     */
    public static void entityToDTO(Object source, Object target) {
        if (source != null) {
            BeanUtil.copyProperties(source, target);
        }
    }

    /**
     * dto转entity
     * @param source
     * @param target
     */
    public static void DTOToEntity(Object source, Object target,String... ignoreProperties) {
        if (source != null && target != null) {
            BeanUtil.copyProperties(source, target,"id","version","del_flag","create_user_id","create_date","update_user_id","update_date", Arrays.toString(ignoreProperties));
        }
    }
}
