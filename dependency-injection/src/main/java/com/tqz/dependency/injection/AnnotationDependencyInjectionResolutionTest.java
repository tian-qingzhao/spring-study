package com.tqz.dependency.injection;

import com.tqz.dependency.injection.annotation.InjectUser;
import com.tqz.dependency.injection.annotation.MyAutowired;
import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * <p>依赖注入的处理过程，入口 {@link DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String)}
 * {@link DependencyDescriptor} 依赖描述符
 * {@link AutowireCandidateResolver} 自动绑定候选对象处理器
 *
 * @author tianqingzhao
 * @since 2021/4/11 22:54
 */
public class AnnotationDependencyInjectionResolutionTest {

    @Autowired
    @Lazy // 延迟默认为 CGLIB 代理过的
    private User lazyUser;

    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional;

    @Inject
    private User injectedUser;

    @InjectUser
    private User myInjectUser;

    // 这种方式可能会报运行时异常 如 ClassNotFoundException。因为不知道这些注解是否存在项目中
/*    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowiredClassTypes = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectUser.class));
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredClassTypes);
        return beanPostProcessor;
    }*/

    // 参考 AutowiredAnnotationBeanPostProcessor 类中的 order 属性为 Ordered.LOWEST_PRECEDENCE - 2
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionTest.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionTest bean = applicationContext.getBean(AnnotationDependencyInjectionResolutionTest.class);
        System.out.println(bean.user);
        System.out.println(bean.users);
        System.out.println(bean.userOptional);
        System.out.println(bean.lazyUser);
        System.out.println(bean.injectedUser);
        System.out.println(bean.myInjectUser);

        applicationContext.close();
    }

}
