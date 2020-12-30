package com.github.zhuobinchan.distributed.lock.spring.configuration;

import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;
import com.github.zhuobinchan.distributed.lock.core.config.RedissonConfig;
import com.github.zhuobinchan.distributed.lock.core.config.ZookeeperConfig;
import com.github.zhuobinchan.distributed.lock.core.temple.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.core.temple.RedissonDistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.core.temple.ZookeeperDistributedLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuobin chan on 2020-12-22 15:23
 */
@Configuration
@ComponentScan("com.github.zhuobinchan.distributed.lock.spring.*")
public class DistributedLockAutoConfiguration {

    @Autowired
    private DistributedLockProperties properties;

    @Bean
    @ConditionalOnProperty(name = "spring.distributed.lock.enable", havingValue = "true")
    public DistributedLockTemplate getTemplate() {
        if (properties.getLockType() == null) {
            throw new NullPointerException("spring.distributed.lock.lock-type property is null");
        }

        DistributedLockConfig distributedLockConfig = properties.getConfig();
        if (distributedLockConfig == null) {
            distributedLockConfig = new DistributedLockConfig();
        }
        if (properties.getLockType() == LockType.ZOOKEPPER) {
            ZookeeperConfig zookeeperConfig = properties.getZookeeperConfig();
            ZookeeperDistributedLockTemplate zookeeperDistributedLockTemplate = new ZookeeperDistributedLockTemplate();
            zookeeperDistributedLockTemplate.setZookeeperConfig(zookeeperConfig);
            zookeeperDistributedLockTemplate.setDistributedLockConfig(distributedLockConfig);
            return zookeeperDistributedLockTemplate;
        }

        if (properties.getLockType() == LockType.REDISSION) {
            RedissonConfig redissonConfig = properties.getRedissonConfig();
            RedissonDistributedLockTemplate redissonDistributedLockTemplate = new RedissonDistributedLockTemplate();
            redissonDistributedLockTemplate.setRedissonConfig(redissonConfig);
            redissonDistributedLockTemplate.setDistributedLockConfig(distributedLockConfig);
            return redissonDistributedLockTemplate;
        }
        throw new NullPointerException("no distributedLockTemplate config");
    }

}
