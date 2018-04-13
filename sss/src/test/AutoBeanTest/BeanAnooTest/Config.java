package test.AutoBeanTest.BeanAnooTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ggq on 2018/1/16.
 */
@Configuration
public class Config {
    @Bean(name = "store",initMethod = "init",destroyMethod = "destroy")
    public StringStore stringStore() {
        return new StringStore();
    }
}
