package com.tqz.dependency.lookup.test;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/5/4 14:38
 */
@Component
@Order(-1)
public class UserServiceImpl implements UserService {
    
//    @Autowired
//    private UserRoleService roleService;
    
    @PostConstruct
    public void init() {
        System.out.println("UserServiceImpl init");
    }
    
    @MyAnnotation
    @Override
    public void say() {
        System.out.println("目标方法执行了...");
    }
}
