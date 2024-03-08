package com.tqz.dependency.lookup.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/4/21 20:13
 */
@RestController
public class UserController {
    
//    @Bean
  /*  public User user() {
        return new User();
    }*/
    
    @RequestMapping("test")
    public String test() {
        System.out.println("业务方法执行了。。。");
        return "===";
    }
}
