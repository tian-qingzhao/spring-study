package com.tqz.dependency.lookup.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/4/21 20:11
 */
@ComponentScan("com.tqz.dependency.lookup.test")
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    
  /*  @Bean
    public User user() {
        return new User(person());
    }
    
    @Bean
    public Person person() {
        return new Person(user());
    }*/
    
//    @Bean
    public User user() {
        return new User();
    }
    
//    @Bean
    public User user1() {
        return new User();
    }
    
//    @Bean(autowire = Autowire.BY_TYPE)
    public Person person() {
        return new Person();
    }
}
