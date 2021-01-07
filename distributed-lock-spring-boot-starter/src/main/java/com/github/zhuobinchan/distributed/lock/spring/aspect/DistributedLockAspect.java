package com.github.zhuobinchan.distributed.lock.spring.aspect;

import com.github.zhuobinchan.distributed.lock.core.temple.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.spring.annotation.DistributedLock;
import com.github.zhuobinchan.distributed.lock.spring.annotation.DistributedLockAnnotationParser;
import com.github.zhuobinchan.distributed.lock.spring.annotation.LockKeySpelView;
import com.github.zhuobinchan.distributed.lock.spring.annotation.SpringDistributedLockAnnotationParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhuobin chan on 2020-12-22 19:19
 */
@Aspect
@Component
public class DistributedLockAspect {
    @Autowired
    private DistributedLockTemplate distributedLockTemplate;

    private final DistributedLockAnnotationParser distributedLockAnnotationParser = new SpringDistributedLockAnnotationParser();

    @Around("@annotation(distributedLock)")
    public Object handle(final ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Object target = proceedingJoinPoint.getTarget();
        String lockKeySpel = distributedLock.key();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String lockKey = distributedLockAnnotationParser.parseDistributedLockAnnotation(new LockKeySpelView(args, target, lockKeySpel, method));

        boolean readInConfig = distributedLock.readInConfig();
        if (readInConfig) {
            return readInConfig(proceedingJoinPoint, lockKey);
        }

        return readInAnnotation(proceedingJoinPoint, distributedLock, lockKey);
    }

    private Object readInConfig(ProceedingJoinPoint proceedingJoinPoint, String lockKey) throws InterruptedException {
        boolean tryLock = distributedLockTemplate.getDistributedLockConfig().isTryLock();
        if (tryLock) {
            return distributedLockTemplate.tryLock(lockKey, () -> proceed(proceedingJoinPoint));
        } else {
            return distributedLockTemplate.lock(lockKey, () -> proceed(proceedingJoinPoint));
        }
    }

    private Object readInAnnotation(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock, String lockKey) throws InterruptedException {
        boolean fairLock = distributedLock.fairLock();
        boolean tryLock = distributedLock.tryLock();
        if (tryLock) {
            return distributedLockTemplate.tryLock(lockKey, () -> proceed(proceedingJoinPoint), distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit(), fairLock);
        } else {
            return distributedLockTemplate.lock(lockKey, () -> proceed(proceedingJoinPoint), distributedLock.leaseTime(), distributedLock.timeUnit(), fairLock);
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
