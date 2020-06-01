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
package com.redekuaizhale.utils.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对象copy工具类
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Slf4j
public class BeanCopyUtils {

    /**
     * 将entityList转换成modelList
     *
     * @param fromList
     * @param tClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> entityListToDTOList(List<F> fromList, Class<T> tClass) {
        if (CollectionUtils.isEmpty(fromList)) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = entityToDTO(f, tClass);
            tList.add(t);
        }
        return tList;
    }

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
        if (entity == null || modelClass == null) {
            return null;
        }
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error("entityToDTO : 实例化异常", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error("entityToDTO : 安全权限异常", e);
        }
        BeanUtils.copyProperties(entity, model);
        return (T) model;
    }

    /**
     * dto转entity
     * @param source
     * @param target
     */
    public static void DTOToEntity(Object source, Object target,String... ignoreProperties) {
        BeanUtils.copyProperties(source, target,"id","version","del_flag","create_user_id","create_date","update_user_id","update_date", Arrays.toString(ignoreProperties));
    }
}
