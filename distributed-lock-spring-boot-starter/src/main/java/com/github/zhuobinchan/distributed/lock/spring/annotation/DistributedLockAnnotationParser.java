package com.github.zhuobinchan.distributed.lock.spring.annotation;

/**
 * @author zhuobin chan on 2020-12-22 19:01
 */
public interface DistributedLockAnnotationParser {

    /**
     * @param view 注解处理类
     * @return 返回对应的加锁key
     */
    String parseDistributedLockAnnotation(LockKeySpelView view);
}
