package com.tqz.aop.features.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 异常通知的拦截器实现。
 * 只支持方法名为 afterThrowing ，且参数是1个或者4个，
 * 有两个 afterThrowing 方法的话，只会调用一个，因为底层用的map存储。
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/18 11:12
 * @see org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
 */
public class MyThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(RuntimeException e) {
        System.out.printf("Exception : %s\n", e);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.printf("Method : %s , args : %s , target : %s, exception : %s\n",
                method,
                Arrays.asList(args),
                target,
                e
        );
    }
}
