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
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectJAdvisorsBuilder;

import java.util.Random;

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
    }

    /**
     * 环绕通知，该通知的执行业务方法之前的调用会比前置通知早，执行业务方法付之后的调用会比后置通知晚。
     * 如果想让前置通知比环绕通知执行业务方法之前的调用早，需要再定义一个 aspect 切面，
     * 并通过 Ordered 控制前置通知比环绕通知的切面早执行即可。
     *
     * <p>几种通知的日志输入如下，这里不考虑异常通知的情况。
     * <ul>
     * <li>@Before any public method.(2)</li>
     * <li>@Around before any public method.</li>
     * <li>@Before any public method.</li>
     * <li>业务逻辑方法</li>
     * <li> @AfterReturning any public method.</li>
     * <li>@After any public method.</li>
     * <li>@Around after any public method.</li>
     * </ul>
     *
     * @param pjp 连接点
     * @return 返回业务方法的返回值
     * @throws Throwable 异常
     * @see BeanFactoryAspectJAdvisorsBuilder#buildAspectJAdvisors()
     */
    @Around("anyPublicMethod()")         // Join Point 拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around before any public method.");
        // 一定要调用 proceed 方法，否则后面的逻辑都不会执行
        Object result = pjp.proceed();
        System.out.println("@Around after any public method.");

        return result;
    }

    @Before("anyPublicMethod()")          // Join Point 拦截动作
    public void beforeAnyPublicMethod() throws Throwable {
        Random random = new Random();

        if (random.nextBoolean()) {
            throw new RuntimeException("For Purpose.");
        }
        System.out.println("@Before any public method.");
    }

    /**
     * 后置通知总是执行，且后置通知比最终返回通知执行的早
     */
    @After("anyPublicMethod()")
    public void finalizeAnyPublicMethod() {
        System.out.println("@After any public method.");
    }

    /**
     * 最终返回通知比后置通知执行的晚，有异常的时候最终返回通知不会执行
     * 和异常通知只能共存一个
     */
    @AfterReturning("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    /**
     * 异常通知，有异常时候才会执行，和最终返回通知只能共存一个
     */
    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method");
    }
}
