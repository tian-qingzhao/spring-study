package com.tqz.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/7 21:08
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 最先执行初始化
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 注解标注的 init() 初始化中。。。");
    }

    // 最后执行初始化
    public void initUserFactory() {
        System.out.println("@Bean 注解的 initMethod() 初始化中。。。");
    }

    // 其次执行初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现 InitializingBean 接口的 afterPropertiesSet() 初始化中。。。");
    }

    // 最先执行销毁
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PostConstruct 注解标注的方法销毁中。。。");
    }

    // 其次执行销毁
    @Override
    public void destroy() throws Exception {
        System.out.println("实现 DisposableBean 接口的 destroy() 方法销毁中。。。");
    }

    // 最后执行销毁
    public void doDestroy() {
        System.out.println("@Bean 注解的 doDestroy() 方法销毁中。。。");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("触发了 GC 垃圾回收机制。。。");
    }
}
