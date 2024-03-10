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
package com.tqz.aop.overview.interceptor;

import com.tqz.aop.overview.DefaultEchoService;
import com.tqz.aop.overview.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP 拦截器示例
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/8 14:44
 */
public class AopInterceptorTest {

    public static void main(String[] args) {
        // 前置模式 + 后置模式
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor beforeInterceptor = (proxy14, method14, args14) -> System.currentTimeMillis();
                    Long startTime = 0L;
                    Long endTime = 0L;
                    Object result = null;
                    try {
                        // 前置拦截
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        result = echoService.echo((String) args[0]); // 目标对象执行
                        // 方法执行后置拦截器
                        AfterReturnInterceptor afterReturnInterceptor = (proxy13, method13, args13, returnResult) -> System.currentTimeMillis();
                        // 执行 after
                        endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
                    } catch (Exception e) {
                        // 异常拦截器（处理方法执行后）
                        ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {
                            System.out.println("有异常了，异常信息：" + throwable.getMessage());
                        };
                        interceptor.intercept(proxy, method, args, e);
                    } finally {
                        // finally 后置拦截器
                        Long finalEndTime = endTime;
                        Long finalStartTime = startTime;
                        FinallyInterceptor interceptor = (proxy12, method12, args12, returnResult) -> {
                            // 方法执行时间（毫秒）
                            return "返回结果" + returnResult + " 耗时 " + (finalEndTime - finalStartTime);
                        };
                        String costTime = (String) interceptor.finalize(proxy, method, args, result);
                        System.out.println("echo 方法执行的实现：" + costTime + " ms.");
                    }
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }
}