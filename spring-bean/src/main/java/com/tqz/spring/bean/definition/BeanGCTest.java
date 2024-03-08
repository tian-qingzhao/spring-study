package com.tqz.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/7 22:54
 */
public class BeanGCTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationTest.class);
        applicationContext.refresh();

        System.out.println("Spring 上下文已启动。。。");

        System.out.println("Spring 上下文准备销毁。。。");
        applicationContext.close();

        System.gc();
    }
}
