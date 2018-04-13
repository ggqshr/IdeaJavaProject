package test.AutoBeanTest.BeanpropertiseTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by ggq on 2018/1/16.
 */
@Configuration
@ImportResource("classpath:config.xml")
public class DriverConfig {
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${pwd}")
    private String pwd;

    @Bean(name = "driver")
    public MydriverManager mydriverManager() {
        return new MydriverManager(url, username, pwd);
    }
}
