package com.tqz.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 16:06
 */
@Component
public class A implements SmartInstantiationAwareBeanPostProcessor {

    @Autowired
    private B b;

    /**
     * 只有循环依赖的时候， getEarlyBeanReference 方法才会被调用，
     * 也就是 getSingleton(beanName, true) 方法调用了之后，
     * 走到了 singletonObject = singletonFactory.getObject() ，
     * 通过 singletonFactory.getObject() 方法执行的时候，这里调用的之前三级缓存池里面缓存的 lambad 表达式，
     * 也就是在 doCreateBean 方法里面执行的 addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean)); 去保存的
     *
     * @param bean     the raw bean instance
     * @param beanName the name of the bean
     * @return 返回早期引用的bean
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("bean：" + bean);
        return bean;
    }
}
