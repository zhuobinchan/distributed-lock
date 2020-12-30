# distributed-lock-core
# 非spring boot 整合例子

1、添加jar包引入
```xml
<dependency>
    <groupId>com.github.zhuobinchan</groupId>
    <artifactId>distributed-lock-core</artifactId>
    <version>2.0</version>
</dependency>
```

2、使用示例:
```java
public class DistributedLockTestCase {

    //Redisson 方式实现分布式锁
    @Test
    public void testSingleRedisLock(){
        RedissonConfig redissonConfig = new RedissonConfig();
        redissonConfig.setAddress("redis://127.0.0.1:6379");

        RedissonDistributedLockTemplate redissonDistributedLockTemplate = new RedissonDistributedLockTemplate();
        redissonDistributedLockTemplate.setRedissonConfig(redissonConfig);
        redissonDistributedLockTemplate.setDistributedLockConfig(new DistributedLockConfig());

        ((DistributedLockTemplate) redissonDistributedLockTemplate).lock("test",()->"test-a");

    }

    //Zookeeper 方式实现分布式锁
    @Test
    public void testSingleZookeeperLock(){
        ZookeeperConfig zookeeperConfig = new ZookeeperConfig();
        zookeeperConfig.setConnectString("127.0.0.1:2181");

        ZookeeperDistributedLockTemplate zookeeperDistributedLockTemplate = new ZookeeperDistributedLockTemplate();
        zookeeperDistributedLockTemplate.setZookeeperConfig(zookeeperConfig);
        zookeeperDistributedLockTemplate.setDistributedLockConfig(new DistributedLockConfig());

        ((DistributedLockTemplate) zookeeperDistributedLockTemplate).lock("test",()->"test-a");

    }
}
```

TODO:
    2、测试集群模式下的分布式锁实现