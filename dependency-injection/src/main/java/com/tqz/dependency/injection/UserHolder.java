package com.tqz.dependency.injection;

import com.tqz.ioc.continer.domain.User;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/11 22:47
 */
public class UserHolder {

    private User user;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
