package com.tqz.ioc.continer.dependency.continer;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p>注解能力 {@link ApplicationContext} 作为 IoC 容器示例
 *
 * @author tianqingzhao
 * @since 2021/4/5 22:51
 */
@Configuration
public class AnnotationApplicationContextAsIoCContinerTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIoCContinerTest.class);
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1);
        user.setName("tianqingzhao");
        return user;
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
}
