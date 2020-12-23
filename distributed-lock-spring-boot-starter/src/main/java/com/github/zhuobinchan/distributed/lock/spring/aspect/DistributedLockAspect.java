package com.github.zhuobinchan.distributed.lock.spring.aspect;

import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockCallable;
import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.spring.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhuobin chan on 2020-12-22 19:19
 */
@Aspect
@Component
public class DistributedLockAspect {
    @Autowired
    private DistributedLockTemplate distributedLockTemplate;

    @Around("@annotation(distributedLock)")
    public Object handle(final ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Object target = proceedingJoinPoint.getTarget();

        boolean fairLock = distributedLock.fairLock();
        boolean tryLock = distributedLock.tryLock();

        //TODO lockKey set
        String lockKey = "";
        if (tryLock) {
            return distributedLockTemplate.tryLock(lockKey, new DistributedLockCallable<Object>() {
                public Object call() {
                    return proceed(proceedingJoinPoint);
                }
            }, fairLock);
        } else {
            return distributedLockTemplate.lock(lockKey, new DistributedLockCallable<Object>() {
                public Object call() {
                    return proceed(proceedingJoinPoint);
                }
            }, fairLock);
        }
    }

    public Object proceed(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
