package com.tqz.dependency.lookup.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p> 循环依赖文章：<here>https://blog.csdn.net/qq_18221459/article/details/106624162</here>
 *
 * @author tianqingzhao
 * @since 2022/4/21 20:11
 */
public class TestAppConfig {
    
//    private static Logger logger = Logger.getLogger(TestAppConfig.class);
    
    private static Logger logger = LoggerFactory.getLogger(TestAppConfig.class);
    
    public static void main(String[] args) {
        logger.debug("111");
        
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
//        UserService userService = context.getBean(UserService.class);
//        userService.say();
    
        UserRoleService roleService = context.getBean(UserRoleService.class);
        roleService.getRole();
    
        //        User user = context.getBean(User.class);
        //        System.out.println(user.getPerson());
        //
        //        Person person =context.getBean(Person.class);
        //        System.out.println(person.getUser());
    }
}
