package com.tqz.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 通过注解驱动aspectj
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/10 19:56
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AspectJAnnotationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AspectJAnnotationTest.class);
        context.refresh();

        AspectJAnnotationTest bean = context.getBean(AspectJAnnotationTest.class);

        context.close();
    }
}
