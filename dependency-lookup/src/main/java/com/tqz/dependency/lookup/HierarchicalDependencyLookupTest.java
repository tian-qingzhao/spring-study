package com.tqz.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 层次性依赖查找实例
 *
 * @author tianqingzhao
 * @since 2021/4/10 13:30
 */
public class HierarchicalDependencyLookupTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupTest.class);

        // ConfigurableListableBeanFactory -> ConfigurableBeanFactory -> HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 中的 Parent BeanFactory:" + beanFactory.getParentBeanFactory());

        // 设置 BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 中的 Parent BeanFactory:" + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        // 启动应用上下文
        applicationContext.refresh();

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory hierarchicalBeanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] :    %s\n",
                hierarchicalBeanFactory,
                hierarchicalBeanFactory.getBean(beanName),
                containsBean(hierarchicalBeanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory hierarchicalBeanFactory, String beanName) {
        BeanFactory parentBeanFactory = hierarchicalBeanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory beanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(beanFactory, beanName)) {
                return true;
            }
        }
        return hierarchicalBeanFactory.containsLocalBean(beanName);
    }

    /**
     * 判断容器中是否包含某个 Bean
     *
     * @param hierarchicalBeanFactory
     * @param beanName
     */
    private static void displayContainsLocalBean(HierarchicalBeanFactory hierarchicalBeanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] :    %s\n",
                hierarchicalBeanFactory,
                hierarchicalBeanFactory.getBean(beanName),
                hierarchicalBeanFactory.containsLocalBean(beanName));
    }

    /**
     * 创建 {@link org.springframework.beans.factory.BeanFactory}
     *
     * @return
     */
    private static HierarchicalBeanFactory createBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
