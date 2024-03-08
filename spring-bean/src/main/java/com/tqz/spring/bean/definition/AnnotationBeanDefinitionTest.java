package com.tqz.spring.bean.definition;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/6 22:07
 */
@Import(AnnotationBeanDefinitionTest.AppConfig.class)
public class AnnotationBeanDefinitionTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionTest.class);

        // 命名 Bean 的注册方法
        registerBeanDefinition(applicationContext, "tian-User");
        // 非命名 Bean 的注册方法
        registerBeanDefinition(applicationContext);

        applicationContext.refresh();

        System.out.println("AppConfig 类型的 Beans ：" + applicationContext.getBeansOfType(AppConfig.class));
        System.out.println("User 类型的 Beans ：" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    /**
     * 有 beanName 的方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 3)
                .addPropertyValue("name", "tianqingzhao");

        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    /**
     * 没有 beanName 的方式
     *
     * @param registry
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerBeanDefinition(registry, null);
    }

    @Component
    public class AppConfig {

        @Bean(name = {"user", "tqz-User"})
        public User user() {
            User user = new User();
            user.setId(2);
            user.setName("tianqingzhao");
            return user;
        }
    }
}
