package com.tqz.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/8 14:07
 */
public class TargetFilterTest {

    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "com.tqz.aop.overview.EchoService";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Class<?> targetClass = classLoader.loadClass(targetClassName);

        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(targetMethod);

        // 查询方法名为 echo ， 参数类型为 String ， 抛出的异常为 NullPointerException
        ReflectionUtils.doWithMethods(targetClass, method -> {
            System.out.println("方法参数类型仅 String，抛出的异常仅 NullPointerException 的方法为：" + method);
        }, method -> {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] exceptionTypes = method.getExceptionTypes();

            return parameterTypes.length == 1 && String.class.equals(parameterTypes[0])
                    && exceptionTypes.length == 1 && NullPointerException.class.equals(exceptionTypes[0]);
        });
    }
}
