package com.tqz.dependency.injection;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * <p>基于 Annotation 依赖 字段 方法进行示例
 *
 * @author tianqingzhao
 * @since 2021/4/18 13:37
 */
public class AnnotationDependencyFieldInjectionTest {

    @Autowired
    private UserHolder userHolder3;

    @Resource
    private UserHolder userHolder2;

    // @Inject 需要依赖 JSR-330 的依赖

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionTest.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        AnnotationDependencyFieldInjectionTest bean = applicationContext.getBean(AnnotationDependencyFieldInjectionTest.class);
        System.out.println(bean.userHolder3);
        System.out.println(bean.userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
