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
package com.redekuaizhale.service;

import com.redekuaizhale.base.exception.ServiceException;
import com.redekuaizhale.base.service.BaseService;
import com.redekuaizhale.dto.user.RequestLoginUserDTO;
import com.redekuaizhale.dto.user.ResponseUserDTO;
import com.redekuaizhale.entity.User;
import com.redekuaizhale.repository.UserRepository;
import com.redekuaizhale.utils.redis.RedisUtils;
import com.redekuaizhale.utils.user.UserThreadLocalUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    public void setRository(UserRepository userRepository) {
        super.baseRepository = userRepository;
    }

    public ResponseUserDTO getLoginUser(RequestLoginUserDTO request) {
        Map<String, Object> queryMap = new HashMap<>(2);
        queryMap.put("loginCode", request.getLoginCode());
        queryMap.put("password", request.getPassword());
        User user = findByProperties(queryMap);
        if (user == null) {
            throw new ServiceException("用户名或密码不正确");
        }
        String token = UUID.randomUUID().toString();
        redisUtils.set(token,user.getId());

        ResponseUserDTO userDTO = new ResponseUserDTO();
        BeanUtils.copyProperties(user,userDTO);

        userDTO.setToken(token);
        return userDTO;
    }
}
