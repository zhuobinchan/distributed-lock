package com.benchan.distributed.lock.spring.annotation;

import java.lang.reflect.Method;

/**
 * @author zhuobin chan on 2020-12-22 19:04
 */
public class SpringDistributedLockAnnotationParser implements DistributedLockAnnotationParser {
    public String parseDistributedLockAnnotation(Method method) {
        //TODO 处理配置分布锁注解

//        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        return null;
    }
}
