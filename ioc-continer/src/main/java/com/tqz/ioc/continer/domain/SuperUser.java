package com.tqz.ioc.continer.domain;

import com.tqz.ioc.continer.annotation.Super;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2021/4/5 14:23
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
