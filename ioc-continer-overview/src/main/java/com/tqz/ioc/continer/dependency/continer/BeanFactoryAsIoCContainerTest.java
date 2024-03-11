package com.tqz.ioc.continer.dependency.continer;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * <p>{@link BeanFactory} 作为 IoC 容器示例
 *
 * @author tianqingzhao
 * @since 2021/4/5 22:56
 */
public class BeanFactoryAsIoCContainerTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println("容器中bean的个数：" + beanDefinitionCount);

        lookupCollectionByType(beanFactory);
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
