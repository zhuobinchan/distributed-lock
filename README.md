# distributed-lock

## 非spring整合
redission 方式实现分布式锁
maven添加配置文件
```xml
<dependency>
    <groupId>com.github.zhuobinchan</groupId>
    <artifactId>distributed-lock-core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

使用示例:
```java
public class DistributedLockTestCase {
    @Test
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
}
```


## 整合spring boot 例子
maven添加配置文件
```xml
<dependency>
	<groupId>>com.github.zhuobinchan</groupId>
	<artifactId>distributed-lock-spring-boot-starter</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

在application文件上添加对应的注解@EnableDistributedLock
```java
@EnableDistributedLock
@SpringBootApplication
public class DistributedLockSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockSpringBootDemoApplication.class, args);
	}

}
```

properties配置文件上添加
```properties
spring.distributed.lock.enable=true
spring.distributed.lock.lock-type=redission
spring.distributed.lock.redisson-config.address=redis://ip:port
```

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
		template.lock("test",()->{
			return "test-a";
		});
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
        DistributedLockTemplateUtils.lock("test", () -> {
            return "test-a";
        });
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

## 如果需要编译成自己项目，请将一下文件进行删除
```xml
<!--  必须配置GPG插件用于使用以下配置对组件进行签名 -->
            <!-- GPG -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-gpg-plugin</artifactId>
                <version>1.6</version>
                <executions>
                    <execution>
                        <phase>verify</phase>
                        <goals>
                            <goal>sign</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## 将会支持列表

 序号      | 功能列表     | 是否支持  
 -------- | :-----------:  | :-----------: 
 1     | 支持redission实现发布锁     | 支持  
 2     | 支持zookeeper实现发布锁     | 未来支持
 3     | 补充测试用例     | 未来支持
 4     | 集群模式的分布式锁实现     | 未来支持