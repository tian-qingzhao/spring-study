package com.tqz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 实现类B
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/3/15 14:09
 */
@Component
public class InstanceB {

    @Autowired
    private IApi instanceA;

    public InstanceB(IApi instanceA) {
        this.instanceA = instanceA;
    }

    public IApi getInstanceA() {
        return instanceA;
    }

    public void setInstanceA(IApi instanceA) {
        this.instanceA = instanceA;
    }


    public InstanceB() {
        System.out.println("InstanceB实例化");
    }

}
