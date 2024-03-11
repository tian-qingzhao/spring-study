package com.tqz.aop.features;

import com.tqz.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.tqz.aop.overview.DefaultEchoService;
import com.tqz.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link ProxyFactoryTest} 的案例
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/10 20:56
 */
public class ProxyFactoryTest {

    public static void main(String[] args) {
        EchoService echoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());

        // invocation 会空指针异常
        // proxyFactory.setTargetClass(DefaultEchoService.class);

        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.echo("hello world"));
    }
}
