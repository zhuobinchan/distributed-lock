package com.benchan.distributed.lock.core.core.temple;

import com.benchan.distributed.lock.core.config.DistributedLockConfig;
import com.benchan.distributed.lock.core.config.RedissonConfig;
import com.benchan.distributed.lock.core.core.DistributedLockTemplate;
import com.benchan.distributed.lock.core.core.DistributedLockCallable;
import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuobin chan on 2020-12-01 11:30
 */
public class RedissonDistributedLockTemplate implements DistributedLockTemplate {
    private RedissonConfig redissonConfig = new RedissonConfig();
    private DistributedLockConfig distributedLockConfig = new DistributedLockConfig();
    private Config config = new Config();
    private RedissonClient redissonClient;

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback) {
        return this.lock(lockKey, callback, distributedLockConfig.isFairLock());
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) {
        return this.lock(lockKey, callback, distributedLockConfig.getDefaultTimeout(), distributedLockConfig.getDefaultTimeUnit(), fairLock);
    }

    @Override
    public <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        RLock lock = getLock(lockKey, fairLock);
        try {
            lock.lock(leaseTime, timeUnit);
            return callback.call();
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException {
        return this.tryLock(lockKey, callback, distributedLockConfig.isFairLock());
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException {
        return this.tryLock(lockKey, callback, distributedLockConfig.getDefaultWaitTime(), distributedLockConfig.getDefaultTimeout(), distributedLockConfig.getDefaultTimeUnit(), fairLock);
    }

    @Override
    public <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException {
        RLock lock = getLock(lockKey, fairLock);
        try {
            if (lock.tryLock(waitTime, leaseTime, timeUnit)) {
                return callback.call();
            }
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    public void setRedissonConfig(RedissonConfig redissonConfig) {
        this.redissonConfig = redissonConfig;
        configRedisson(this.redissonConfig);
    }

    public void setDistributedLockConfig(DistributedLockConfig distributedLockConfig) {
        this.distributedLockConfig = distributedLockConfig;
    }

    private void configRedisson(RedissonConfig redissonConfig) {
        if (isNotBlank(redissonConfig.getMasterName())) {
            this.config = toSentinelConfig(redissonConfig);
        } else {
            this.config = toSingleConfig(redissonConfig);
        }

        this.redissonClient = Redisson.create(config);
    }

    private Config toSentinelConfig(RedissonConfig redissonConfig) {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redissonConfig.getSentinelAddresses())
                .setMasterName(redissonConfig.getMasterName()).setTimeout(redissonConfig.getTimeout())
                .setMasterConnectionPoolSize(redissonConfig.getMasterConnectionPoolSize()).setSlaveConnectionPoolSize(redissonConfig.getSlaveConnectionPoolSize())
                .setTcpNoDelay(true).setDatabase(redissonConfig.getDatabase()).setDnsMonitoringInterval(redissonConfig.getDnsMonitoringInterval())
                .setSubscriptionConnectionMinimumIdleSize(redissonConfig.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redissonConfig.getSubscriptionConnectionPoolSize())
                .setSubscriptionsPerConnection(redissonConfig.getSubscriptionsPerConnection()).setClientName(redissonConfig.getClientName())
                .setRetryAttempts(redissonConfig.getRetryAttempts()).setRetryInterval(redissonConfig.getRetryInterval()).setTimeout(redissonConfig.getTimeout())
                .setConnectTimeout(redissonConfig.getConnectTimeout()).setIdleConnectionTimeout(redissonConfig.getIdleConnectionTimeout())
                .setPassword(redissonConfig.getPassword());

        if (isNotBlank(redissonConfig.getPassword())) {
            serverConfig.setPassword(redissonConfig.getPassword());
        }

        config.setThreads(redissonConfig.getThread());
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        config.setEventLoopGroup(new NioEventLoopGroup());
        return config;
    }

    private Config toSingleConfig(RedissonConfig redissonConfig) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer().setAddress(redissonConfig.getAddress()).setTcpNoDelay(true)
                .setConnectionMinimumIdleSize(redissonConfig.getConnectionMinimumIdleSize()).setConnectionPoolSize(redissonConfig.getConnectionPoolSize())
                .setDatabase(redissonConfig.getDatabase()).setDnsMonitoringInterval(redissonConfig.getDnsMonitoringInterval())
                .setSubscriptionConnectionMinimumIdleSize(redissonConfig.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redissonConfig.getSubscriptionConnectionPoolSize())
                .setSubscriptionsPerConnection(redissonConfig.getSubscriptionsPerConnection()).setClientName(redissonConfig.getClientName())
                .setRetryAttempts(redissonConfig.getRetryAttempts()).setRetryInterval(redissonConfig.getRetryInterval()).setTimeout(redissonConfig.getTimeout())
                .setConnectTimeout(redissonConfig.getConnectTimeout()).setIdleConnectionTimeout(redissonConfig.getIdleConnectionTimeout())
                .setPassword(redissonConfig.getPassword());

        if (isNotBlank(redissonConfig.getPassword())) {
            serverConfig.setPassword(redissonConfig.getPassword());
        }

        config.setThreads(redissonConfig.getThread());
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        config.setEventLoopGroup(new NioEventLoopGroup());
        return config;

    }

    private RLock getLock(String lockName, boolean fairLock) {
        RLock lock;
        if (fairLock) {
            lock = redissonClient.getFairLock(lockName);
        } else {
            lock = redissonClient.getLock(lockName);
        }
        return lock;
    }

    private boolean isNotBlank(String value) {
        return value != null && !"".equals(value);
    }
}
