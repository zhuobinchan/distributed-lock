import com.github.zhuobinchan.distributed.lock.core.config.DistributedLockConfig;
import com.github.zhuobinchan.distributed.lock.core.config.RedissonConfig;
import com.github.zhuobinchan.distributed.lock.core.config.ZookeeperConfig;
import com.github.zhuobinchan.distributed.lock.core.temple.DistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.core.temple.RedissonDistributedLockTemplate;
import com.github.zhuobinchan.distributed.lock.core.temple.ZookeeperDistributedLockTemplate;
import org.junit.jupiter.api.Test;

/**
 * @author zhuobin chan on 2020-12-22 11:03
 */
public class DistributedLockTestCase {
    @Test
    public void testSingleRedisLock() {
        RedissonConfig redissonConfig = new RedissonConfig();
        redissonConfig.setAddress("redis://127.0.0.1:6379");

        RedissonDistributedLockTemplate redissonDistributedLockTemplate = new RedissonDistributedLockTemplate();
        redissonDistributedLockTemplate.setRedissonConfig(redissonConfig);
        redissonDistributedLockTemplate.setDistributedLockConfig(new DistributedLockConfig());

        ((DistributedLockTemplate) redissonDistributedLockTemplate).lock("test", () -> "test-a");

    }

    @Test
    public void testSingleZookeeperLock() {
        ZookeeperConfig zookeeperConfig = new ZookeeperConfig();
        zookeeperConfig.setConnectString("127.0.0.1:2181");

        ZookeeperDistributedLockTemplate zookeeperDistributedLockTemplate = new ZookeeperDistributedLockTemplate();
        zookeeperDistributedLockTemplate.setZookeeperConfig(zookeeperConfig);
        zookeeperDistributedLockTemplate.setDistributedLockConfig(new DistributedLockConfig());

        ((DistributedLockTemplate) zookeeperDistributedLockTemplate).lock("test", () -> "test-a");

    }
}
