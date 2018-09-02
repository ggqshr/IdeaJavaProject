package ggq.config;

import ggq.filter.MyFilter;
import ggq.listener.MyListener;
import ggq.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {
    //    EmbeddedServletContainerCustomizer被WebServerFactoryCustomizer替代
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8080);
            }
        };
    }

    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet() {
        //将/myservlet请求映射到MyServlet上
        return new ServletRegistrationBean<MyServlet>(new MyServlet(), "/myservlet");
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myservlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        return new ServletListenerRegistrationBean<MyListener>(new MyListener());
    }
}
