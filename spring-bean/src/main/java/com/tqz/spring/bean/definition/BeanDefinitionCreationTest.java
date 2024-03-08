package com.tqz.spring.bean.definition;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * <p>构建 Bean
 *
 * @author tianqingzhao
 * @since 2021/4/6 21:10
 */
public class BeanDefinitionCreationTest {
    
    public static void main(String[] args) {
        // 1.通过 BeanDefinitionBuilder 构建对象
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder.addPropertyValue("id", 1);
        beanDefinitionBuilder.addPropertyValue("name", "tianqingzhao");
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非 Bean 的终态，还可以自定义修改
        
        //2.通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        //        beanDefinitionBuilder.addPropertyValue("id", 1);
        //        beanDefinitionBuilder.addPropertyValue("name", "tianqingzhao");
        mutablePropertyValues.add("id", 1).add("name", "tianqingzhao");
        // 通过 set MutablePropertyValues 批量操作
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
