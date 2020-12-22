import com.benchan.distributed.lock.config.DistributedLockConfig;
import com.benchan.distributed.lock.config.RedissonConfig;
import com.benchan.distributed.lock.core.DistributedLockTemplate;
import com.benchan.distributed.lock.core.temple.RedissonDistributedLockTemplate;
import org.junit.jupiter.api.Test;

/**
 * @author zhuobin chan on 2020-12-22 11:03
 */
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
