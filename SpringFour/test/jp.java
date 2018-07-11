import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jp {
    @Test
    public void tt() {
        Jedis jedis = new Jedis("localhost");
        jedis.lpush("test","1");
        jedis.lpush("test","2");
        jedis.lpush("test","3");
    }
}
