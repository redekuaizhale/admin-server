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
package com.zh.usermenu.dto;

import com.zh.usermenu.entity.UserMenuEntity;
import com.zh.utils.bean.CopyBeanUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户菜单响应DTO
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Data
public class ResponseUserMenuDTO extends UserMenuDTO {

    public static ResponseUserMenuDTO toDTO(UserMenuEntity entity) {
        ResponseUserMenuDTO dto = new ResponseUserMenuDTO();
        CopyBeanUtil.entityToDTO(entity,dto);
        return dto;
    }

    public static List<ResponseUserMenuDTO> toDTOList(List<UserMenuEntity> entityList) {
        List<ResponseUserMenuDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
}
