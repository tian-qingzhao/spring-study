package com.tqz.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml驱动aspectj
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/10 19:56
 */
@Configuration
@Aspect
public class AspectJXmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-aop-context.xml");

//        AspectJXmlTest bean = context.getBean(AspectJXmlTest.class);

        context.close();
    }
}
