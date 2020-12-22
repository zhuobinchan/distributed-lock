# distributed-lock-core

redission 方式实现分布式锁
使用示例:

```java
    public void testSingleLock(){
        RedissonConfig redissonConfig = new RedissonConfig();
        redissonConfig.setAddress("redis://*");

        RedissonDistributedLockTemplate redissonDistributedLockTemplate = new RedissonDistributedLockTemplate();
        redissonDistributedLockTemplate.setRedissonConfig(redissonConfig);
        redissonDistributedLockTemplate.setDistributedLockConfig(new DistributedLockConfig());

        DistributedLockTemplate template = redissonDistributedLockTemplate;
        template.lock("test",()->{
            return "test-a";
        });
    }
```

TODO:
    1、添加zookeeper的实现方式
    2、测试集群模式下的分布式锁实现