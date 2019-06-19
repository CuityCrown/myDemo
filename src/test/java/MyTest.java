import com.ryml.enums.RedisCommonEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private RedisTemplate<String,Integer> redisTemplate;

    @Test
    public void test(){
        ValueOperations<String, Integer> stringIntegerValueOperations = redisTemplate.opsForValue();
        stringIntegerValueOperations.set(RedisCommonEnum.STUDENT.getValue(),1);
        System.out.println(stringIntegerValueOperations.get(RedisCommonEnum.STUDENT.getValue()));
    }

}
