package com.github.zhuobinchan.distributed.lock.spring.context;

import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhuobin chan on 2020-12-23 18:36
 */
@Component
public class DistributedLockTemplateContext {
    @Autowired
    private DistributedLockTemplate distributedLockTemplate;

    private static DistributedLockTemplate staticTemplate;

    @PostConstruct
    public void initStaticTemplate() {
        staticTemplate = distributedLockTemplate;
    }

    public static DistributedLockTemplate getStaticTemplate() {
        return staticTemplate;
    }
}
