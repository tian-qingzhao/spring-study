package com.tqz.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * <p>基于 XML 资源的依赖 Constructor 方法进行示例
 *
 * @author tianqingzhao
 * @since 2021/4/18 13:38
 */
public class XmlDependencyConstructorInjectionTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "/META-INF/dependency-constructor-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
