package com.github.zhuobinchan.distributed.lock.spring.utils;

import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockCallable;
import com.github.zhuobinchan.distributed.lock.spring.context.DistributedLockTemplateContext;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-23 18:35
 */
public class DistributedLockTemplateUtils {

    public static <V> V lock(String lockKey, DistributedLockCallable<V> callback) {
        return DistributedLockTemplateContext.getStaticTemplate().lock(lockKey, callback);
    }

    public static <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) {
        return DistributedLockTemplateContext.getStaticTemplate().lock(lockKey, callback, fairLock);
    }

    public static <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        return DistributedLockTemplateContext.getStaticTemplate().lock(lockKey, callback, leaseTime, timeUnit, fairLock);
    }

    public static <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException {
        return DistributedLockTemplateContext.getStaticTemplate().tryLock(lockKey, callback);
    }

    public static <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException {
        return DistributedLockTemplateContext.getStaticTemplate().tryLock(lockKey, callback, fairLock);
    }

    public static <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException {
        return DistributedLockTemplateContext.getStaticTemplate().tryLock(lockKey, callback, waitTime, leaseTime, timeUnit, fairLock);
    }
}
