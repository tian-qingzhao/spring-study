/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tqz.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 注解 配置类
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/10 20:21
 */
@Aspect
public class AspectAnnotationConfiguration {

    @Pointcut("execution(public * *(..))") // 匹配 Join Point
    private void anyPublicMethod() { // 方法名即 Pointcut 名
        System.out.println("@Pointcut at any public method.");
    }

    @Around("anyPublicMethod()")         // Join Point 拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method.");
        return pjp.proceed();
    }

    @Before("anyPublicMethod()")          // Join Point 拦截动作
    public void beforeAnyPublicMethod() throws Throwable {
        System.out.println("@Before any public method.");
    }
}
