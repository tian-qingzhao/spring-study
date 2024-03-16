package com.tqz.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 16:01
 */
@ComponentScan("com.tqz.spring")
@EnableAspectJAutoProxy
public class SpringIocTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringIocTest.class);

//        A bean = context.getBean(A.class);
        System.out.println("===");
    }

}
