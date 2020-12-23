package com.github.zhuobinchan.distributed.lock.spring.annotation;

import java.lang.reflect.Method;

/**
 * @author zhuobin chan on 2020-12-23 10:40
 */
public class LockKeySpelView {
    private Object[] args;
    private Object target;
    private String lockKeySpel;
    private Method method;

    public LockKeySpelView(Object[] args, Object target, String lockKeySpel, Method method) {
        this.args = args;
        this.target = target;
        this.lockKeySpel = lockKeySpel;
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getLockKeySpel() {
        return lockKeySpel;
    }

    public void setLockKeySpel(String lockKeySpel) {
        this.lockKeySpel = lockKeySpel;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
