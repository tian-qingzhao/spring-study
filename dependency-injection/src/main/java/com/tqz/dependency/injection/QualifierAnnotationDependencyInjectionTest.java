package com.tqz.dependency.injection;

import com.tqz.dependency.injection.annotation.UserGroup;
import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * <p>基于 Annotation 依赖 Setter 方法进行示例
 *
 * @author tianqingzhao
 * @since 2021/4/11 22:54
 */
public class QualifierAnnotationDependencyInjectionTest {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionTest.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionTest bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionTest.class);
        System.out.println(bean.user);
        System.out.println(bean.namedUser);
        System.out.println(bean.allUsers);
        System.out.println(bean.qualifierUsers);
        System.out.println(bean.groupUsers);

        applicationContext.close();
    }

    @Bean
    @Qualifier
    public User user11() {
        return createUser(7);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10);
    }

    public User createUser(Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }

}
