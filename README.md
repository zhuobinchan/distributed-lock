# distributed-lock




# 整合spring boot 例子

第1步：
maven添加配置文件
```xml
<dependency>
	<groupId>>com.github.zhuobinchan</groupId>
	<artifactId>distributed-lock-spring-boot-starter</artifactId>
	<version>2.0</version>
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

第4步: 可以用一下集中方式使用分布式锁


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