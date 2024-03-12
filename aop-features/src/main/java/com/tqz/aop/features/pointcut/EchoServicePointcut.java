package com.tqz.aop.features.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * {@link com.tqz.aop.overview.EchoService} 的匹配点
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/12 13:34
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private final String methodName;

    private final Class<?> targetClass;

    public EchoServicePointcut(String methodName, Class<?> targetClass) {
        this.methodName = methodName;
        this.targetClass = targetClass;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(method.getName(), this.methodName) &&
                this.targetClass.isAssignableFrom(targetClass);
    }
}
