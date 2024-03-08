package com.tqz.dependency.injection;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * <p>基于 Annotation 依赖 方法 方法进行示例
 *
 * @author tianqingzhao
 * @since 2021/4/18 13:37
 */
public class AnnotationDependencyMethodInjectionTest {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    // @Inject 需要依赖 JSR-330 的依赖

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionTest.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        AnnotationDependencyMethodInjectionTest bean = applicationContext.getBean(AnnotationDependencyMethodInjectionTest.class);
        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }
}
