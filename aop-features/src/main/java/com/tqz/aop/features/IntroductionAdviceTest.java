package com.tqz.aop.features;

import com.tqz.aop.overview.EchoService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.util.Map;

/**
 * 通过 {@link org.springframework.aop.IntroductionInfo} 限制代理对象的接口。
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/18 14:37
 */
public class IntroductionAdviceTest implements EchoService, Comparable<IntroductionAdviceTest> {

    public static void main(String[] args) {
        IntroductionAdviceTest target = new IntroductionAdviceTest();

        // 这种方式不能限制
//        ProxyFactory proxyFactory = new ProxyFactory(target);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(
                        (MethodBeforeAdvice)
                                (method, args1, target1) -> System.out.println("BeforeAdvice : " + method),
                        () -> new Class[]{EchoService.class, Map.class}
                )
        );

        Object proxy = proxyFactory.getProxy();

        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello world");

        Map map = (Map) proxy;
        map.put("test", "A");

        // 虽然目标对象实现了 Comparable 接口，但是通过 IntroductionInfo 限制了被代理对象实现的接口
        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return null;
    }

    @Override
    public int compareTo(IntroductionAdviceTest o) {
        return 0;
    }
}
