package com.github.zhuobinchan.distributed.lock.core.temple;

import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;
import com.github.zhuobinchan.distributed.lock.core.config.ZookeeperConfig;
import com.github.zhuobinchan.distributed.lock.core.lock.zookeeper.FairLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author zhuobin chan on 2020-12-29 17:17
 */
public class ZookeeperDistributedLockTemplate implements DistributedLockTemplate {
    private ZookeeperConfig zookeeperConfig = new ZookeeperConfig();
    private DistributedLockConfig distributedLockConfig = new DistributedLockConfig();
    private CuratorFramework curatorFramework;

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback) {
        return this.lock(lockKey, callback, distributedLockConfig.isFairLock());
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) {
        return this.lock(lockKey, callback, distributedLockConfig.getDefaultTimeout(), distributedLockConfig.getDefaultTimeUnit(), fairLock);
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        Lock lock = getLock(lockKey);
        try {
            lock.lock();
            return callback.call();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException {
        return this.tryLock(lockKey, callback, distributedLockConfig.isFairLock());
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException {
        return this.tryLock(lockKey, callback, distributedLockConfig.getDefaultWaitTime(), distributedLockConfig.getDefaultTimeout(), distributedLockConfig.getDefaultTimeUnit(), fairLock);
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException {
        Lock lock = getLock(lockKey);
        try {
            lock.tryLock(waitTime, timeUnit);
            return callback.call();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setDistributedLockConfig(DistributedLockConfig distributedLockConfig) {
        this.distributedLockConfig = distributedLockConfig;
    }

    @Override
    public DistributedLockConfig getDistributedLockConfig() {
        return this.distributedLockConfig;
    }

    private Lock getLock(String lockName) {
        return new FairLock(curatorFramework, this.zookeeperConfig.getRootPath() + lockName);
    }

    public void setZookeeperConfig(ZookeeperConfig zookeeperConfig) {
        this.zookeeperConfig = zookeeperConfig;
        configZookeeper(this.zookeeperConfig);
    }

    private void configZookeeper(ZookeeperConfig zookeeperConfig) {
        this.curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConfig.getConnectString(), new ExponentialBackoffRetry(this.zookeeperConfig.getBaseSleepTimeMs(), this.zookeeperConfig.getMaxRetries()));
        this.curatorFramework.start();
    }
}
