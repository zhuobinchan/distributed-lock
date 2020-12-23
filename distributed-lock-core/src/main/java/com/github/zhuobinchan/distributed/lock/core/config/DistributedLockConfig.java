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

    /**
     * 是否忽略前缀, 每个分布式锁默认都会加上当前项目和当前方法作为前缀, 当需要跨项目进行分布式锁时, 可使用忽略前缀从而达到多个项目使用同一个分布式锁
     */
    private boolean ignorePrefix = false;

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

    public boolean isIgnorePrefix() {
        return ignorePrefix;
    }

    public void setIgnorePrefix(boolean ignorePrefix) {
        this.ignorePrefix = ignorePrefix;
    }
}
