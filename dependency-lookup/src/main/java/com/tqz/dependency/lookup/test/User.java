package com.tqz.dependency.lookup.test;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/4/21 20:12
 */
//@Component
public class User {
    
    @Resource
    private Person persons;
    
    public User() {
    
    }
    
    public User(Person person) {
        System.out.println("user实例化了。。。");
    }
    
    @PostConstruct
    public void init() {
        System.out.println("user初始化了");
    }
    
    public String say() {
        return "hello";
    }
    
    public Person getPerson() {
        return persons;
    }
    
}
