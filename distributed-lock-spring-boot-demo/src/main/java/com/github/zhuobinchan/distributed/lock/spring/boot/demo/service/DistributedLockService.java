package com.github.zhuobinchan.distributed.lock.spring.boot.demo.service;

/**
 * @author zhuobin chan on 2020-12-23 11:20
 */
public interface DistributedLockService {

    String testLockByAnnotation();

    String testLockByAnnotationWithArg(String lockKey1);

    String testLockByAnnotation(String lockKey1);

    String testLockByAnnotation(TestLockView testLockView);
}
