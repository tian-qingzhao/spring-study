package com.tqz.ioc.continer.dependency.lookup;

import com.tqz.ioc.continer.annotation.Super;
import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * <p>依赖查找的示例
 *
 * @author tianqingzhao
 * @since 2021/4/5 13:56
 */
public class DependencyLookupTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");

        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);

        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupByAnnotationType(beanFactory);
    }

    /**
     * 延时查找
     *
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时查找:" + user);
    }

    /**
     * 实时查找
     *
     * @param beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找:" + user);
    }

    /**
     * 根据类型查找
     *
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找返回一个对象：" + user);
    }

    /**
     * 根据类型查找返回一个集合
     *
     * @param beanFactory
     */
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型查找返回一个集合:" + beans);
        }
    }

    /**
     * 根据注解查找
     *
     * @param beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("注解查找：" + beans);
        }
    }
}
