package com.tqz.ioc.continer.domain;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/5 13:54
 */
public class User {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(2);
        user.setName("田青钊");
        return user;
    }
}
