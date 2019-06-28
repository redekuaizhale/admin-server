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
package com.redekuaizhale;

import com.redekuaizhale.entity.User;
import com.redekuaizhale.service.UserService;
import com.redekuaizhale.utils.redis.RedisUtils;
import com.redekuaizhale.utils.user.UserThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanghui
 * @date 2019-06-24
 * @company Dingxuan
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            log.error(">>>> token 未传输 >>>>");
            return false;
        }
        String userId = redisUtils.get(token);
        if (StringUtils.isEmpty(userId)) {
            log.error(">>>> token 不存在 >>>>");
            return false;
        }
        User user = userService.findById(userId);
        if (user == null) {
            log.error(">>>> token 已失效");
            return false;
        }
        UserThreadLocalUtils.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalUtils.remove();
    }
}
