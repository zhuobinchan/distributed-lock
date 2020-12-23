package com.github.zhuobinchan.distributed.lock.spring.annotation;

import com.github.zhuobinchan.distributed.lock.spring.configuration.DistributedLockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DistributedLockAutoConfiguration.class})
public @interface EnableDistributedLock {
}