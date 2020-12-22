package com.benchan.distributed.lock.spring.annotation;

import com.benchan.distributed.lock.spring.configuration.DistributedLockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DistributedLockAutoConfiguration.class})
public @interface EnableDistributedLock {
}