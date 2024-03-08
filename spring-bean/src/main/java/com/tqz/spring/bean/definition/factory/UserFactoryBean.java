package com.tqz.spring.bean.definition.factory;

import com.tqz.ioc.continer.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * <p> 通过 {@link FactoryBean} 创建 Bean 对象
 *
 * @autoor tianqingzhao
 * @since 2021/4/7 21:14
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
