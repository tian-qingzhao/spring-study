package com.tqz.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * <p>自动绑定注入，byName 或者 byType
 *
 * @author tianqingzhao
 * @since 2021/4/11 23:14
 */
public class AutowiringByNameDependencySettingInjectionTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "/META-INF/autowiring-dependency-setter-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
