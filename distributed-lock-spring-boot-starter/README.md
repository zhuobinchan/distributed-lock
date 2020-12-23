# distributed-lock

# 整合spring boot 例子
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
然后注入使用即可
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