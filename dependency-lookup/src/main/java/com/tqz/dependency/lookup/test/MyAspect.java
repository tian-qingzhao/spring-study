package com.tqz.dependency.lookup.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/5/3 19:47
 */
@Component
@Aspect
public class MyAspect {
    
    @Pointcut("@annotation(com.tqz.dependency.lookup.test.MyAnnotation)")
    public void pointcut() {
    
    }
    
    @Before("pointcut()")
    public void before(JoinPoint joinpoint) {
        Method method = getMethod(joinpoint);
        
        System.out.println("前置通知... 【" + method.getName() + "】 方法执行了。。。");
    }
    
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        
        System.out.println("后置通知... 【" + method.getName() + "】 方法执行了。。。");
    }
    
    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        
        System.out.println("最终返回... 【" + method.getName() + "】 方法执行了。。。");
    }
    
    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        
        System.out.println("异常通知... 【" + method.getName() + "】 方法执行了。。。");
    }
    
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
    
        System.out.println("环绕通知前置... 【" + method.getName() + "】 方法执行了。。。");
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    
        System.out.println("环绕通知后置... 【" + method.getName() + "】 方法执行了。。。");
    }
    
    private Method getMethod(JoinPoint joinpoint) {
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        return signature.getMethod();
    }
}
