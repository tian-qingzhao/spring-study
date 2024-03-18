package com.tqz.aop.features;

import com.tqz.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.tqz.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import com.tqz.aop.features.pointcut.EchoServicePointcut;
import com.tqz.aop.overview.DefaultEchoService;
import com.tqz.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/12 13:54
 */
public class PointcutApiTest {

    public static void main(String[] args) {
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        EchoServiceEchoMethodPointcut echoServiceEchoMethodPointcut = EchoServiceEchoMethodPointcut.INSTANCE;

        ComposablePointcut pointcut = new ComposablePointcut(echoServiceEchoMethodPointcut);
        pointcut.intersection(echoServicePointcut.getClassFilter());
        pointcut.intersection(echoServicePointcut.getMethodMatcher());

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(
                pointcut, new EchoServiceMethodInterceptor());

        ProxyFactory proxyFactory = new ProxyFactory(new DefaultEchoService());

        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.echo("hello world"));
    }
}
