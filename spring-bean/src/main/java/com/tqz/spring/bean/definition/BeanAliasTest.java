package com.tqz.spring.bean.definition;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> Bean 别名测试
 *
 * @autoor tianqingzhao
 * @since 2021/4/6 21:55
 */
public class BeanAliasTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definitions-context.xml");

        User user = beanFactory.getBean("user", User.class);
        User tqzUser = beanFactory.getBean("tqz-user", User.class);
        System.out.println(user == tqzUser);
    }
}
