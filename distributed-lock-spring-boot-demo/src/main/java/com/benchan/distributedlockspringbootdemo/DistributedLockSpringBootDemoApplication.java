package com.benchan.distributedlockspringbootdemo;

import com.benchan.distributed.lock.spring.annotation.EnableDistributedLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDistributedLock
@SpringBootApplication
public class DistributedLockSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockSpringBootDemoApplication.class, args);
	}

}
