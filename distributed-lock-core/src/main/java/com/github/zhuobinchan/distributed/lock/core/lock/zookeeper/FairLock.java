package com.github.zhuobinchan.distributed.lock.core.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhuobin chan on 2020-12-29 18:44
 */
public class FairLock implements Lock {
    private CuratorFramework lockClient;
    private String lockKey;
    private InterProcessMutex interProcessMutex;

    public FairLock(CuratorFramework lockClient, String lockKey) {
        this.lockClient = lockClient;
        this.lockKey = lockKey;
        interProcessMutex = new InterProcessMutex(lockClient, lockKey);
    }

    @Override
    public void lock() {
        try {
            this.interProcessMutex.acquire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        try {
            return this.tryLock(-1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            return interProcessMutex.acquire(time, unit);
        } catch (Exception e) {
            InterruptedException interruptedException = new InterruptedException();
            interruptedException.addSuppressed(e);
            throw interruptedException;
        }
    }

    @Override
    public void unlock() {
        try {
            interProcessMutex.release();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
