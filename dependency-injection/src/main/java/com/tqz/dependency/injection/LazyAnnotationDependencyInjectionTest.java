package com.tqz.dependency.injection;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>基于 {@link ObjectProvider} 实现延迟注入
 *
 * @author tianqingzhao
 * @since 2021/4/19 22:44
 */
public class LazyAnnotationDependencyInjectionTest {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 继承自 ObjectFactory 接口

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionTest.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionTest bean = applicationContext.getBean(LazyAnnotationDependencyInjectionTest.class);
        System.out.println(bean.user);
        System.out.println(bean.userObjectProvider.getObject());
        System.out.println(bean.userObjectFactory.getObject());

        applicationContext.close();
    }

}
