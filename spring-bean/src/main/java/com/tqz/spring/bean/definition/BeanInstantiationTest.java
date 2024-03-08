package com.tqz.spring.bean.definition;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> Bean 创建的实例
 *
 * @author tianqingzhao
 * @since 2021/4/7 21:04
 */
public class BeanInstantiationTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("userByStaticMethod", User.class);
        User userByInstanceBean = beanFactory.getBean("userByInstanceBean", User.class);
        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceBean);
    }
}
