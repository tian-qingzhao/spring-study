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

import com.tqz.aop.features.advice.MyThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Random;

/**
 * {@link org.springframework.aop.ThrowsAdvice} 的示例。
 * 使用 {@link org.aspectj.lang.annotation.AfterThrowing} 注解的话，
 * 是使用的 {@link org.springframework.aop.aspectj.AspectJAfterThrowingAdvice} 逻辑，
 * 而没有使用 {@link org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor} 的逻辑。
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/18 11:10
 * @see org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
 */
public class ThrowsAdviceTest {

    public static void main(String[] args) throws Exception {

        ThrowsAdviceTest instance = new ThrowsAdviceTest();

        ProxyFactory proxyFactory = new ProxyFactory(instance);

        proxyFactory.addAdvice(new MyThrowsAdvice());

        ThrowsAdviceTest proxy = (ThrowsAdviceTest) proxyFactory.getProxy();
        proxy.execute();
        proxy.execute();
    }

    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For Purpose.");
        }
        System.out.println("Executing...");
    }
}
