package com.tqz.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 * 通过 {@link ObjectProvider} 进行依赖查找
 *
 * @author tianqingzhao
 * @since 2021/4/10 13:00
 */
public class ObjectProviderTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderTest.class);

        // 启动应用上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
//        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> iterable = objectProvider;
//        for (String str : iterable) {
//            System.out.println(str);
//        }
//        objectProvider.stream().forEach(System.out::println);
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello World!";
    }

    @Bean
    public String test() {
        return "test";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
//        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        System.out.println(beanProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
//        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
//        User user = beanProvider.getIfAvailable(User::createUser);
//        System.out.println("当前 User 对象：" + user);
    }
}
