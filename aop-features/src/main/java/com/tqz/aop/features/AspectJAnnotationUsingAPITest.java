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
package com.tqz.aop.features;

import com.tqz.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

import java.util.HashMap;
import java.util.Map;

/**
 * aspect使用api案例
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/10 20:22
 */
public class AspectJAnnotationUsingAPITest {

    public static void main(String[] args) {

        // 通过创建一个 HashMap 缓存，作为被代理对象
        Map<String, Object> cache = new HashMap<>();
        // 创建 Proxy 工厂(AspectJ)
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 增加 Aspect 配置类
        proxyFactory.addAspect(AspectConfiguration.class);
        // 设置暴露代理对象到 AopContext
        proxyFactory.setExposeProxy(true);
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args1, target) -> {
            if ("put".equals(method.getName()) && args1.length == 2) {
                Object proxy = AopContext.currentProxy();
                System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" +
                        "代理对象：%s\n", args1[0], args1[1], proxy);
            }
        });


        // 存储数据
        // cache.put("1", "A");
        // 通过代理对象存储数据
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(cache.get("1"));

    }
}
