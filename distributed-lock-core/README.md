# distributed-com.benchan.lock
distributed com.benchan.lock impl

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
