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
package com.zh.utils.threadlocal;

import com.zh.base.exception.ServiceException;
import com.zh.user.entity.UserEntity;

/**
 * 用户线程工具类
 * @author zhanghui
 * @date 2019-06-24
 * @company Dingxuan
 */
public class UserThreadLocalUtils {

    private static final ThreadLocal<UserEntity> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserEntity user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static UserEntity get() {
        UserEntity user  = USER_THREAD_LOCAL.get();
        if (user == null) {
            throw new ServiceException("登录失效，请重新登录！");
        }
        return user;
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }
}
