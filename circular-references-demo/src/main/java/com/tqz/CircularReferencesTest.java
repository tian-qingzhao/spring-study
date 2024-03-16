package com.tqz;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 循环依赖测试
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 14:47
 */
public class CircularReferencesTest {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    // 一级缓存
    public Map<String, Object> singletonObjects = new ConcurrentHashMap<>();


    // 二级缓存： 为了将 成熟Bean和纯净Bean分离，避免读取到不完整得Bean
    public Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    // 三级缓存
    public Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();

    // 循环依赖标识
    public Set<String> singletonsCurrennlyInCreation = new HashSet<>();

    private static JdkProxyBeanPostProcessor jdkProxyBeanPostProcessor = new JdkProxyBeanPostProcessor();

    public static void main(String[] args) throws Exception {
        CircularReferencesTest context = new CircularReferencesTest();

        // 加载了BeanDefinition
        context.loadBeanDefinitions();

        // 注册Bean的后置处理器
        context.addBeanPostProcessor(jdkProxyBeanPostProcessor);

        // 循环创建Bean
        for (String key : context.beanDefinitionMap.keySet()) {
            // 先创建A
            context.getBean(key);
        }

        IApi instanceA = (IApi) context.getBean("instanceA");
        instanceA.say();
    }

    private void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    // 假设A 使用了Aop @PointCut("execution(* *..InstanceA.*(..))")   要给A创建动态代理
    // 获取Bean
    public Object getBean(String beanName) throws Exception {
        return doGetBean(beanName);
    }

    private Object doGetBean(String beanName) throws Exception {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 正在创建
        singletonsCurrennlyInCreation.add(beanName);

        // 这里直接模拟 doCreateBean ，spring是 createBean
        Object instanceBean = doCreateBean(beanName);

        afterSingletonCreation(beanName);

        addSingleton(beanName, instanceBean);

        // remove 二级缓存和三级缓存
        return instanceBean;
    }

    private void afterSingletonCreation(String beanName) {
        singletonsCurrennlyInCreation.remove(beanName);
    }

    private void addSingleton(String beanName, Object instanceBean) {
        // 添加到一级缓存，并从二级缓存、三级缓存移除
        singletonObjects.put(beanName, instanceBean);
        singletonFactories.remove(beanName);
        earlySingletonObjects.remove(beanName);
    }

    private Object doCreateBean(String beanName) throws Exception {
        // 实例化
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();

        Object instanceBean = createBeanInstance(beanClass);

        // 创建动态代理  （耦合 、BeanPostProcessor)    Spring还是希望正常的Bean 还是再初始化后创建
        // 只在循环依赖的情况下在实例化后创建proxy   判断当前是不是循环依赖
        addSingletonFactory(beanName, () -> jdkProxyBeanPostProcessor.getEarlyBeanReference(instanceBean, beanName));

        populateBean(beanClass, instanceBean);

        Object exposedObject = instanceBean;

        // spring这里又做了额外的处理，所以没直接返回
        exposedObject = initializeBean(beanName, exposedObject);

        return exposedObject;
    }

    private void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory) {
        singletonFactories.put(beanName, objectFactory);
        earlySingletonObjects.remove(beanName);
    }

    private Object initializeBean(String beanName, Object bean) throws Exception {
        invokeAwareMethods(beanName, bean);

        Object wrappedBean = bean;

        // 后置处理器前置通知
        wrappedBean = applyBeanPostProcessorsBeforeInitialization(beanName, wrappedBean);

        // 执行初始化方法
        invokeInitMethods(wrappedBean);

        // 后置处理器后置通知
        wrappedBean = applyBeanPostProcessorsAfterInitialization(beanName, bean);

        return wrappedBean;
    }

    private void invokeInitMethods(Object bean) throws Exception {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }

    private Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object existingBean) {
        Object result = existingBean;

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }

        return result;
    }

    private Object applyBeanPostProcessorsAfterInitialization(String beanName, Object existingBean) {
        Object result = existingBean;
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }


    private void invokeAwareMethods(String beanName, Object bean) {
        // 回调aware接口，这里用一个举例
        if (bean instanceof BeanNameAware) {
            ((BeanNameAware) bean).setBeanName(beanName);
        }
    }

    private void populateBean(Class<?> beanClass, Object instanceBean) throws Exception {
        // 属性赋值
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Autowired annotation = declaredField.getAnnotation(Autowired.class);
            // 说明属性上面有Autowired
            if (annotation != null) {
                declaredField.setAccessible(true);
                // byname  bytype  byconstrator
                // instanceB
                String name = declaredField.getName();
                Object fileObject = getBean(name);   //拿到B得Bean
                declaredField.set(instanceBean, fileObject);
            }
        }
    }

    private Object createBeanInstance(Class<?> beanClass) throws Exception {
        return beanClass.newInstance();  // 通过无参构造函数
    }

    public Object getSingleton(String beanName) {
        // 先从一级缓存中拿
        Object singletonObject = singletonObjects.get(beanName);

        // 说明是循环依赖
        if (singletonObject == null && singletonsCurrennlyInCreation.contains(beanName)) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 如果二级缓存没有就从三级缓存中拿
            if (singletonObject == null) {
                // 从三级缓存中拿
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    // 这里的 getObject 也就是调用的 doCreateBean 方法里面的 () -> getEarlyBeanReference(beanName, mbd, bean)
                    singletonObject = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }

        return singletonObject;
    }

    /**
     * 读取bean定义，当然在spring中肯定是根据配置 动态扫描注册
     */
    public void loadBeanDefinitions() {
        RootBeanDefinition aBeanDefinition = new RootBeanDefinition(InstanceA.class);
        RootBeanDefinition bBeanDefinition = new RootBeanDefinition(InstanceB.class);
        beanDefinitionMap.put("instanceA", aBeanDefinition);
        beanDefinitionMap.put("instanceB", bBeanDefinition);
    }
}
