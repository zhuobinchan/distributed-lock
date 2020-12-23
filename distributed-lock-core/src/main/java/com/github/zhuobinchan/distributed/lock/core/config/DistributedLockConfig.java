package com.github.zhuobinchan.distributed.lock.core.config;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-01 10:57
 */
public class DistributedLockConfig {

    /**
     * 是否使用公平锁。 公平锁即先来先得。
     */
    private boolean fairLock = false;

    /**
     * 是否优先使用尝试锁
     */
    private boolean tryLock = true;

    /**
     * 默认等待时间
     */
    private long defaultWaitTime = 60;

    /**
     * 锁超时时间
     */
    private long defaultTimeout = 20;

    /**
     * 时间单位。默认为秒。
     */
    private TimeUnit defaultTimeUnit = TimeUnit.SECONDS;

    public boolean isFairLock() {
        return fairLock;
    }

    public void setFairLock(boolean fairLock) {
        this.fairLock = fairLock;
    }

    public boolean isTryLock() {
        return tryLock;
    }

    public void setTryLock(boolean tryLock) {
        this.tryLock = tryLock;
    }

    public long getDefaultWaitTime() {
        return defaultWaitTime;
    }

    public void setDefaultWaitTime(long defaultWaitTime) {
        this.defaultWaitTime = defaultWaitTime;
    }

    public long getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public TimeUnit getDefaultTimeUnit() {
        return defaultTimeUnit;
    }

    public void setDefaultTimeUnit(TimeUnit defaultTimeUnit) {
        this.defaultTimeUnit = defaultTimeUnit;
    }

}
