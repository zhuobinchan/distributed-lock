package com.benchan.distributedlockspringbootdemo;

import com.benchan.distributed.lock.core.core.DistributedLockTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
