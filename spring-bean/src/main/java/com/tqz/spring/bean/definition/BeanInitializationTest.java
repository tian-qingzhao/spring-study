package com.tqz.spring.bean.definition;

import com.tqz.spring.bean.definition.factory.DefaultUserFactory;
import com.tqz.spring.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> 初始化实例
 *
 * @author tianqingzhao
 * @since 2021/4/7 22:00
 */
@Configuration
public class BeanInitializationTest {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationTest.class);
        applicationContext.refresh();
        
        System.out.println("Spring 上下文已启动。。。");
        
        System.out.println("Spring 上下文准备销毁。。。");
        applicationContext.close();
        System.out.println("Spring 上下文已销毁。。。");
    }
    
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    //    @Lazy // 延时加载的 Bean 在上下文启动的时候不会初始化，只有在被调用的时候才会被初始化
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
