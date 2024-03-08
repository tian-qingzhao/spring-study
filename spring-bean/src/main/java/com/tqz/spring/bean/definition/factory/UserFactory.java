package com.tqz.spring.bean.definition.factory;

import com.tqz.ioc.continer.domain.User;

/**
 * <p>
 *
 * @autoor tianqingzhao
 * @since 2021/4/7 21:07
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

}
