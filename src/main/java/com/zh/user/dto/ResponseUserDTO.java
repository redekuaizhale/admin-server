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
package com.zh.user.dto;

import com.zh.user.entity.UserEntity;
import com.zh.utils.bean.CopyBeanUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户响应DTO
 *
 * @author zhanghui
 * @date 2019-06-24
 * @company Dingxuan
 */
@Data
public class ResponseUserDTO extends UserDTO{

    /**
     * token
     */
    private String token;

    public static ResponseUserDTO toDTO(UserEntity entity) {
        ResponseUserDTO dto = new ResponseUserDTO();
        CopyBeanUtil.entityToDTO(entity, dto);
        if (entity.getCompanyEntity() != null) {
            dto.setCompanyId(entity.getCompanyEntity().getId());
            dto.setCompanyName(entity.getCompanyEntity().getName());
        }
        if (entity.getDeptEntity() != null) {
            dto.setDeptId(entity.getDeptEntity().getId());
            dto.setDeptName(entity.getDeptEntity().getName());
        }
        return dto;
    }

    public static List<ResponseUserDTO> toDTOList(List<UserEntity> entityList) {
        List<ResponseUserDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
}
