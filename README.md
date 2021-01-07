# distributed-lock

# 非spring boot 整合例子

1、添加jar包引入
```xml
<dependency>
    <groupId>com.github.zhuobinchan</groupId>
    <artifactId>distributed-lock-core</artifactId>
    <version>2.1</version>
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


# distributed-lock
# 非spring boot 整合例子

1、添加jar包引入
```xml
<dependency>
    <groupId>com.github.zhuobinchan</groupId>
    <artifactId>distributed-lock-core</artifactId>
    <version>2.1</version>
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


# spring boot 整合例子

第1步：
maven添加配置文件
```xml
<dependency>
	<groupId>>com.github.zhuobinchan</groupId>
	<artifactId>distributed-lock-spring-boot-starter</artifactId>
    <version>2.1</version>
</dependency>
```

第2步：
在application启动类上添加对应的注解@EnableDistributedLock
```java
@EnableDistributedLock
@SpringBootApplication
public class DistributedLockSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockSpringBootDemoApplication.class, args);
	}

}
```

第3步：在对应配置文件上添加对应的配置

以Redisson作为分布式锁
```properties
spring.distributed.lock.enable=true

# redisson方式实现分布式锁
spring.distributed.lock.lock-type=redisson
spring.distributed.lock.redisson-config.address=redis://127.0.0.1:6379
```

以Zookeeper作为分布式锁
```properties
spring.distributed.lock.enable=true

# zookeeper方式实现分布式锁
spring.distributed.lock.lock-type=zookeeper
spring.distributed.lock.zookeeper-config.connect-string=127.0.0.1:2181
```

第4步: 可以用以下集中方式使用分布式锁


使用方式一：
注入方式使用即可
```java
@SpringBootTest
@RunWith(SpringRunner.class)
class DistributedLockSpringBootDemoApplicationTests {

	@Autowired
	private DistributedLockTemplate template;

	@Test
	void contextLoads() {
		template.lock("test",()->"test-a");
	}

}
```

使用方式二：
静态类调用方式：
```java
@SpringBootTest
@RunWith(SpringRunner.class)
class DistributedLockSpringBootDemoApplicationTests {
    @Test
    void templateUtilsTest() {
        DistributedLockTemplateUtils.lock("test", () -> "test-a");
    }
}
```

使用方式三：
需要使用由spring进行管理，因为是通过spring的aop进行实现，用法和cache的注解类似
```java
@Service
public class DistributedLockServiceImpl implements DistributedLockService {

    @Override
    @DistributedLock(key = "testKey1")
    public String testLockByAnnotation() {
        return "testKey1";
    }

    @Override
    @DistributedLock(key = "lockKey1")
    public String testLockByAnnotationWithArg(String lockKey1) {
        return "testKey1";
    }

    @Override
    @DistributedLock(key = "#lockKey1")
    public String testLockByAnnotation(String lockKey1) {
        return "lockKey1";
    }

    @Override
    @DistributedLock(key = "#testLockView.lockKey2")
    public String testLockByAnnotation(TestLockView testLockView) {
        return "testLockView";
    }
}
```

## 将会支持列表

 序号      | 功能列表     | 是否支持  
 -------- | :-----------:  | :-----------: 
 1     | 支持redission实现发布锁     | 支持  
 2     | 支持zookeeper实现发布锁     | 支持
 3     | 补充测试用例     | 未来支持
 4     | 集群模式的分布式锁实现     | 未来支持
 
 
 
 ## 核心方法参数说明
 非spring boot方式核心类 方法解释
 ```java
public interface DistributedLockTemplate {

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback);

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock);

    /**
     * 直接上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param leaseTime 释放锁时间
     * @param timeUnit 时间单位
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V lock(String lockKey, DistributedLockCallable<V> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback) throws InterruptedException;

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, boolean fairLock) throws InterruptedException;

    /**
     * 尝试上锁
     * @param lockKey 锁键
     * @param callback 需要锁的区域逻辑代码实现
     * @param waitTime 上锁等待时长
     * @param leaseTime 释放锁时间
     * @param timeUnit 时间单位
     * @param fairLock 是否公平锁
     * @param <V> 返回值泛型
     * @return 区域逻辑代码执行返回值
     */
    <V> V tryLock(String lockKey, DistributedLockCallable<V> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws InterruptedException;

    /**
     * 通用配置设置
     * @param distributedLockConfig 通用配置
     */
    void setDistributedLockConfig(DistributedLockConfig distributedLockConfig);

    /**
     * 过去通用配置
     * @return 通用配置
     */
    DistributedLockConfig getDistributedLockConfig();
}
```
## spring boot方式核心类 方法解释
DistributedLockTemplate 以及 DistributedLockTemplateUtils 使用方法同上

注解核心使用方法
```java
public @interface DistributedLock {


    /**
     * @return 分布式锁key值,spring el表达式, 参考cache
     */
    String key();

    /**
     * @return 是否从配置文件读取下面字段的数据
     *
     *
     * 包括以下字段：fairLock{@link #fairLock};tryLock{@link #tryLock};waitTime{@link #waitTime};leaseTime{@link #leaseTime};timeUnit{@link #timeUnit};
     */
    boolean readInConfig() default true;

    /**
     * @return 是否使用公平锁。 公平锁即先来先得。
     */
    boolean fairLock() default false;

    /**
     * @return 是否使用尝试锁
     */
    boolean tryLock() default true;

    /**
     * @return 最长等待时间。 该字段只有当tryLock()返回true才有效。
     */
    long waitTime() default 60L;

    /**
     * @return 锁超时时间。 超时时间过后，锁自动释放。 建议： 尽量缩简需要加锁的逻辑。
     */
    long leaseTime() default 20L;

    /**
     * @return 时间单位。默认为秒。
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
```