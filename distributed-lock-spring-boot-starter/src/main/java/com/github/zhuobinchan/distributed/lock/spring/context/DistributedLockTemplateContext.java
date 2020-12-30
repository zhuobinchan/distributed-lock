package com.github.zhuobinchan.distributed.lock.spring.context;

import com.github.zhuobinchan.distributed.lock.core.temple.DistributedLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhuobin chan on 2020-12-23 18:36
 */
@Component
public class DistributedLockTemplateContext {

    private static DistributedLockTemplate staticTemplate;

    @Autowired
    public void initStaticTemplate(DistributedLockTemplate distributedLockTemplate) {
        staticTemplate = distributedLockTemplate;
    }

    public static DistributedLockTemplate getStaticTemplate() {
        return staticTemplate;
    }
}
