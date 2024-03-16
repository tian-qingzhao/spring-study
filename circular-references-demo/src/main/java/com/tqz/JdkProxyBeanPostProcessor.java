package com.tqz;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean后置处理器，模拟spring-aop的 {@link AbstractAutoProxyCreator} 类进行动态代理。
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 14:08
 */
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    private final Map<Object, Boolean> advisedBeans = new ConcurrentHashMap<>(256);

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        return wrapIfNecessary(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return wrapIfNecessary(bean, beanName);
    }

    private Object wrapIfNecessary(Object bean, String beanName) {
        if (Boolean.FALSE.equals(this.advisedBeans.get(beanName))) {
            return bean;
        }

        // 假设:A 被切点命中 需要创建代理  @PointCut("execution(* *..InstanceA.*(..))")
        if (bean instanceof InstanceA) {
            this.advisedBeans.put(beanName, Boolean.TRUE);
            JdkDynimcProxy jdkDynimcProxy = new JdkDynimcProxy(bean);
            return jdkDynimcProxy.getProxy();
        }

        this.advisedBeans.put(beanName, Boolean.FALSE);
        return bean;
    }
}
