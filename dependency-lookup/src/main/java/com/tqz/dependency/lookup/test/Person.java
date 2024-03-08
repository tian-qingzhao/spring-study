package com.tqz.dependency.lookup.test;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/4/29 21:20
 */
//@Component
public class Person {
    
    @Resource
    private User users;
    
    String str;
    
    /*@Autowired(required = false)
    public void setUser(User user) {
        this.user = user;
    }*/
    
    public User getUser() {
        return users;
    }
    
    // 报空指针，因为注入是在实例化之后
    /*public Person() {
        this.str = user.say();
    }*/
    
  /*  public Person(String str) {
    
    }*/
    
   /* public Person(User user) {
    
    }*/
}
