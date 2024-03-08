package com.tqz.spring.bean.definition;

import com.tqz.spring.bean.definition.factory.DefaultUserFactory;
import com.tqz.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * <p> 特殊 Bean 创建的实例
 * {@link AbstractFactoryBean#getObject()} 返回实现 {@link org.springframework.beans.factory.FactoryBean}
 * <p>
 * spring的实现
 * {@link ServiceListFactoryBean#getObjectToExpose(ServiceLoader)} 可以返回多个
 * {@link ServiceFactoryBean#getObjectToExpose(ServiceLoader)} 只会返回一个
 *
 * @author tianqingzhao
 * @since 2021/4/7 21:04
 */
public class SpecialBeanInstantiationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        // 只会返回一个
        serviceLoaderBySingle(userFactoryServiceLoader);
        // 返回多个
        serviceLoadByMultiple();

        // 通过 AutowireCapableBeanFactory 创建 UserFactory
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }


    private static void serviceLoaderBySingle(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }

    private static void serviceLoadByMultiple() {
        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        serviceLoaderBySingle(serviceLoader);
    }
}
