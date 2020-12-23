# spring boot demo
整合spring boot 例子

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

配置文件上添加
```properties
spring.distributed.lock.enable=true
spring.distributed.lock.lock-type=redission
spring.distributed.lock.redisson-config.address=redis://ip:port
```

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