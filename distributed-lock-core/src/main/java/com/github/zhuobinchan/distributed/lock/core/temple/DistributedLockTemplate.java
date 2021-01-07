package com.github.zhuobinchan.distributed.lock.core.temple;

import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 模板 类似 redisTemplate
 *
 * @author zhuobin chan on 2020-12-01 11:15
 */
public interface DistributedLockTemplate {

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback);

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock);

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param leaseTime 释放锁时间
     * @param timeUnit 时间单位
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException;

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException;

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param waitTime 上锁等待时长
     * @param leaseTime 释放锁时间
     * @param timeUnit 时间单位
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException;

    /**
     * 通用配置设置
     * @param distributedLockConfig 通用配置
     */
    void setDistributedLockConfig(DistributedLockConfig distributedLockConfig);

    /**
     * 过去通用配置
     * @return 通用配置
     */
    DistributedLockConfig getDistributedLockConfig();
}
