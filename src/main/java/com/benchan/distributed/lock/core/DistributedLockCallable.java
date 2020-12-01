package com.benchan.distributed.lock.core;

/**
 * @author zhuobin chan on 2020-12-01 10:31
 */
@FunctionalInterface
public interface DistributedLockCallable<V> {

    /**
     * 分布式锁实现逻辑抽象逻辑
     */
    V call();
}
