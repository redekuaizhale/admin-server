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
package com.zh;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 记录接口响应时间
 * @author redekuaizhale
 * @date 2019-06-03
 * @company Dingxuan
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.zh.*.controller..*.*(..))")
    public void logPointcut() {

    }
    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            List<Object> argsObject = new ArrayList<>();
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                argsObject.addAll(Arrays.asList(args));
            }
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("请求URL:【{}】,请求参数:【{}】,返回结果:【{}】,耗时:【{}】s",request.getRequestURL().toString(),argsObject.toString(),result.toString(),end-start);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }
}
