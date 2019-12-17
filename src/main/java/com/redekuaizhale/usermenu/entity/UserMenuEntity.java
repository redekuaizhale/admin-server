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
package com.redekuaizhale.usermenu.entity;

import com.redekuaizhale.base.entity.BaseEntity;
import com.redekuaizhale.menu.entity.MenuEntity;
import com.redekuaizhale.user.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 用户菜单Entity
 * @author zhanghui
 * @date 2019-06-13
 * @company Dingxuan
 */
@Data
@Entity
@Table(name = "t_user_menu")
public class UserMenuEntity extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne()
    @JoinColumn(name = "menu_id")
    private MenuEntity menuEntity;

}
