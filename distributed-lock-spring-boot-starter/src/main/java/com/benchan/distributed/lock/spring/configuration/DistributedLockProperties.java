package com.benchan.distributed.lock.spring.configuration;

import com.benchan.distributed.lock.core.config.DistributedLockConfig;
import com.benchan.distributed.lock.core.config.RedissonConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhuobin chan on 2020-12-22 15:28
 */
@Component
@ConfigurationProperties(
        prefix = "spring.distributed.lock"
)
public class DistributedLockProperties {
    private boolean enable;
    private LockType lockType;
    private DistributedLockConfig config;
    private RedissonConfig redissonConfig;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }

    public DistributedLockConfig getConfig() {
        return config;
    }

    public void setConfig(DistributedLockConfig config) {
        this.config = config;
    }

    public RedissonConfig getRedissonConfig() {
        return redissonConfig;
    }

    public void setRedissonConfig(RedissonConfig redissonConfig) {
        this.redissonConfig = redissonConfig;
    }
}
