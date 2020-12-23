package com.github.zhuobinchan.distributed.lock.spring.boot.demo;

import com.github.zhuobinchan.distributed.lock.core.core.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.spring.boot.demo.service.DistributedLockService;
import com.github.zhuobinchan.distributed.lock.spring.boot.demo.service.TestLockView;
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

    @Autowired
    private DistributedLockService distributedLockService;

    @Test
    void templateTest() {
        template.lock("test", () -> "test-a");
    }

    @Test
    void distributedLockService1() {
        String result = distributedLockService.testLockByAnnotation();
        System.out.println(result);
    }

    @Test
    void distributedLockService2() {
        String result = distributedLockService.testLockByAnnotation("distributedLockService2");
        System.out.println(result);
    }

    @Test
    void distributedLockService3() {
        TestLockView testLockView = new TestLockView();
        testLockView.setLockKey2("distributedLockService3");
        String result = distributedLockService.testLockByAnnotation(testLockView);
        System.out.println(result);
    }

    @Test
    void distributedLockService4() {
        String result = distributedLockService.testLockByAnnotationWithArg("distributedLockService4");
        System.out.println(result);
    }


}
