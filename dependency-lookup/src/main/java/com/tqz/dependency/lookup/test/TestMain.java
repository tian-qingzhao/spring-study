package com.tqz.dependency.lookup.test;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/5/13 17:26
 */
public class TestMain {
    
    public static void main(String[] args) {
        System.out.println(createBean("1", () -> getStr()));
    }
    
    private static String createBean(String s, Aware<?> aware) {
        return s + aware.getStr();
    }
    
    private static Object getStr() {
        return "hello";
    }
    
    static interface Aware<T> {
        
        T getStr();
    }
    
}
