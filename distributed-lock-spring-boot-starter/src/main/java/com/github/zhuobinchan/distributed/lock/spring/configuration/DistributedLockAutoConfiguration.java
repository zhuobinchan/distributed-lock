package com.github.zhuobinchan.distributed.lock.spring.configuration;

import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;
import com.github.zhuobinchan.distributed.lock.core.config.RedissonConfig;
import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.core.core.temple.RedissonDistributedLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuobin chan on 2020-12-22 15:23
 */
@Configuration
@ConditionalOnClass(DistributedLockProperties.class)
@EnableConfigurationProperties(DistributedLockProperties.class)
public class DistributedLockAutoConfiguration {

    @Autowired
    private DistributedLockProperties properties;

    @Bean
    @ConditionalOnProperty(name = "spring.distributed.lock.enable", havingValue = "true")
    public DistributedLockTemplate getTemplate() {
        if (properties.getLockType() == LockType.REDISSION) {
            RedissonConfig redissonConfig = properties.getRedissonConfig();
            DistributedLockConfig distributedLockConfig = properties.getConfig();
            if (distributedLockConfig == null){
                distributedLockConfig = new DistributedLockConfig();
            }

            RedissonDistributedLockTemplate redissonDistributedLockTemplate = new RedissonDistributedLockTemplate();
            redissonDistributedLockTemplate.setRedissonConfig(redissonConfig);
            redissonDistributedLockTemplate.setDistributedLockConfig(distributedLockConfig);
            return redissonDistributedLockTemplate;
        }
        return null;
    }


}
