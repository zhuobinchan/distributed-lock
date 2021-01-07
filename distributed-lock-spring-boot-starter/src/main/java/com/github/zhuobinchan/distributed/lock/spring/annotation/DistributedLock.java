package com.github.zhuobinchan.distributed.lock.spring.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-01 10:31
 */


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {


    /**
     * @return 分布式锁key值,spring el表达式, 参考cache
     */
    String key();

    /**
     * @return 是否从配置文件读取下面字段的数据
     *
     *
     * 包括以下字段：fairLock{@link #fairLock};tryLock{@link #tryLock};waitTime{@link #waitTime};leaseTime{@link #leaseTime};timeUnit{@link #timeUnit};
     */
    boolean readInConfig() default true;

    /**
     * @return 是否使用公平锁。 公平锁即先来先得。
     */
    boolean fairLock() default false;

    /**
     * @return 是否使用尝试锁
     */
    boolean tryLock() default true;

    /**
     * @return 最长等待时间。 该字段只有当tryLock()返回true才有效。
     */
    long waitTime() default 60L;

    /**
     * @return 锁超时时间。 超时时间过后，锁自动释放。 建议： 尽量缩简需要加锁的逻辑。
     */
    long leaseTime() default 20L;

    /**
     * @return 时间单位。默认为秒。
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
