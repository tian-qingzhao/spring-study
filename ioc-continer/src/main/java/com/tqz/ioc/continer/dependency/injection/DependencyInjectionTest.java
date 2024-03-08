package com.tqz.ioc.continer.dependency.injection;

import com.tqz.ioc.continer.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>依赖注入的示例
 *
 * @author tianqingzhao
 * @since 2021/4/5 13:56
 */
public class DependencyInjectionTest {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory());

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();

        System.out.println(userRepository.getBeanFactory() == applicationContext);
        System.out.println(objectFactory.getObject() == applicationContext);

        System.out.println(userRepository.getObjectFactory());
    }
}
