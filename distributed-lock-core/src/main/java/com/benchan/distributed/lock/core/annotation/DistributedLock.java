package com.benchan.distributed.lock.core.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-01 10:31
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {


    /**
     * spring el表达式, 参考cache
     */
    String lockKey();

    /**
     * 是否使用公平锁。 公平锁即先来先得。
     */
    boolean fairLock();

    /**
     * 是否使用尝试锁。
     */
    boolean tryLock();

    /**
     * 最长等待时间。 该字段只有当tryLock()返回true才有效。
     */
    long waitTime();

    /**
     * 锁超时时间。 超时时间过后，锁自动释放。 建议： 尽量缩简需要加锁的逻辑。
     */
    long leaseTime();

    /**
     * 是否忽略前缀, 每个分布式锁默认都会加上当前项目和当前方法作为前缀, 当需要跨项目进行分布式锁时, 可使用忽略前缀从而达到多个项目使用同一个分布式锁
     */

    boolean ignorePrefix();

    /**
     * 时间单位。默认为秒。
     */
    TimeUnit timeUnit();
}
