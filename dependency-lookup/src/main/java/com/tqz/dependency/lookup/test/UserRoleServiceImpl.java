package com.tqz.dependency.lookup.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/5/13 19:01
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Autowired
    private UserService userService;
    
    @PostConstruct
    public void init() {
        System.out.println("RoleServiceImpl init");
    }
    
    @Override
    @MyAnnotation
    public void getRole() {
        System.out.println("role");
    }
}
