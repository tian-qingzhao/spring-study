package com.tqz.aop.overview;

/**
 * {@link ClassLoader} 根据当前类加载器查找所有的父加载器
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/8 13:54
 */
public class ClassLoadingTest {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader); // Launcher$AppClassLoader

        ClassLoader parentClassLoader = classLoader;
        while (true) {
            parentClassLoader = parentClassLoader.getParent();
            if (parentClassLoader == null) { // Launcher$ExtClassLoader ClassLoader
                break;
            }
            System.out.println(parentClassLoader);
        }
    }
}
