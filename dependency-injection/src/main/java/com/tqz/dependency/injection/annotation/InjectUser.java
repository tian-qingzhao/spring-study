package com.tqz.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * <p>自定义依赖注入注解
 *
 * @author tianqingzhao
 * @since 2021/4/20 21:44
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {

}
