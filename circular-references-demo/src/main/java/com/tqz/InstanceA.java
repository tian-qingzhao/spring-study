package com.tqz;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 实现类A
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 14:09
 */
@Component
public class InstanceA implements IApi, BeanPostProcessor {

    @Autowired
    private InstanceB instanceB;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器的 postProcessBeforeInitialization 方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器的 postProcessAfterInitialization 方法");
        return bean;
    }

    public InstanceA() {
        System.out.println("InstanceA实例化");
    }

    @Override
    public void say() {
        System.out.println("I'm A");
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }

    public InstanceB getInstanceB() {
        return instanceB;
    }
}
