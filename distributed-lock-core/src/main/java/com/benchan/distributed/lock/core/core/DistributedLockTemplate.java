package com.benchan.distributed.lock.core.core;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 模板 类似 redisTemplate
 *
 * @author zhuobin chan on 2020-12-01 11:15
 */
public interface DistributedLockTemplate {

    <V> V lock(String lockKey, DistributedLockCallable<V> callback);

    <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock);

    <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException;

    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException;

    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException;
}
