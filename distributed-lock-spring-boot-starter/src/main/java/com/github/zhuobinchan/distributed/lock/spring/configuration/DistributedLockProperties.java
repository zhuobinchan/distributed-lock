package com.github.zhuobinchan.distributed.lock.spring.configuration;

import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;
import com.github.zhuobinchan.distributed.lock.core.config.RedissonConfig;
import com.github.zhuobinchan.distributed.lock.core.config.ZookeeperConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhuobin chan on 2020-12-22 15:28
 */
@Component
@ConfigurationProperties(
        prefix = "spring.distributed.lock", ignoreInvalidFields = true
)
public class DistributedLockProperties {
    private boolean enable;
    private LockType lockType;
    private DistributedLockConfig config;
    private RedissonConfig redissonConfig;
    private ZookeeperConfig zookeeperConfig;

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

    public ZookeeperConfig getZookeeperConfig() {
        return zookeeperConfig;
    }

    public void setZookeeperConfig(ZookeeperConfig zookeeperConfig) {
        this.zookeeperConfig = zookeeperConfig;
    }
}
