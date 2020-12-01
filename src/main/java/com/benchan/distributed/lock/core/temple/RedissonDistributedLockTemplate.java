package com.benchan.distributed.lock.core.temple;

import com.benchan.distributed.lock.config.RedissonConfig;
import com.benchan.distributed.lock.core.DistributedLockCallable;
import com.benchan.distributed.lock.core.DistributedLockTemplate;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-01 11:30
 */
public class RedissonDistributedLockTemplate implements DistributedLockTemplate {
    private RedissonConfig redissonConfig = new RedissonConfig();
    private Config config = new Config();

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback) {
        return null;
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) {
        return null;
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        return null;
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) {
        return null;
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) {
        return null;
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        return null;
    }

    public void setRedissonConfig(RedissonConfig redissonConfig) {
        this.redissonConfig = redissonConfig;
        configRedisson(this.redissonConfig);
    }

    private void configRedisson(RedissonConfig redissonConfig) {
        this.config = new Config();
    }
}
