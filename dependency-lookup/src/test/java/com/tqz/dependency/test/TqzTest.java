package com.tqz.dependency.test;

import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/4/30 13:49
 */
public class TqzTest {
    
    public static void main(String[] args) {
        Set<String> list = new TreeSet<>();
        
        boolean flag = list.add("user");
        boolean flag1 = list.add("user");
    
        System.out.println(flag + " === " + flag1);
        
        
    }
}
