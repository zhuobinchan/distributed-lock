package com.github.zhuobinchan.distributed.lock.spring.annotation;

import java.lang.reflect.Method;

/**
 * @author zhuobin chan on 2020-12-22 19:01
 */
public interface DistributedLockAnnotationParser {

    /**
     * 返回对应的加锁key
     * @param method
     */
    String parseDistributedLockAnnotation(Method method);
}
