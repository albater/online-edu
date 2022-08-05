import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author alpha
 * @className: RedisTest
 * @date 2022/8/2 9:09
 * @Description
 */
@SpringBootTest(classes = RedisTest.class)
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void test(){
        redisTemplate.opsForValue().set("xixi:haha:lele",168,10, TimeUnit.MINUTES);
        Object obj = redisTemplate.opsForValue().get("xixi:haha:lele");
        System.out.println("obj = " + obj);
    }
}
