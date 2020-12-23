package com.github.zhuobinchan.distributed.lock.spring.boot.demo.service.impl;

import com.github.zhuobinchan.distributed.lock.spring.annotation.DistributedLock;
import com.github.zhuobinchan.distributed.lock.spring.boot.demo.service.DistributedLockService;
import com.github.zhuobinchan.distributed.lock.spring.boot.demo.service.TestLockView;
import org.springframework.stereotype.Service;

/**
 * @author zhuobin chan on 2020-12-23 11:21
 */
@Service
public class DistributedLockServiceImpl implements DistributedLockService {

    @Override
    @DistributedLock(key = "testKey1")
    public String testLockByAnnotation() {
        return "testKey1";
    }

    @Override
    @DistributedLock(key = "lockKey1")
    public String testLockByAnnotationWithArg(String lockKey1) {
        return "testKey1";
    }

    @Override
    @DistributedLock(key = "#lockKey1")
    public String testLockByAnnotation(String lockKey1) {
        return "lockKey1";
    }

    @Override
    @DistributedLock(key = "#testLockView.lockKey2")
    public String testLockByAnnotation(TestLockView testLockView) {
        return "testLockView";
    }
}
