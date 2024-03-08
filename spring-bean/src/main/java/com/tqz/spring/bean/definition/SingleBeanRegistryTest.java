package com.tqz.spring.bean.definition;

import com.tqz.spring.bean.definition.factory.DefaultUserFactory;
import com.tqz.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/7 23:10
 */
public class SingleBeanRegistryTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();
        System.out.println("Spring 上下文已启动。。。");

        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println(userFactory == userFactoryByLookup);

        System.out.println("Spring 上下文准备销毁。。。");
        applicationContext.close();
    }
}
