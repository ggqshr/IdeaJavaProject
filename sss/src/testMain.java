import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.AutoBeanTest.BeanpropertiseTest.MydriverManager;

/**
 * Created by ggq on 2018/1/10.
 */
public class testMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        MydriverManager mydriverManager = (MydriverManager) context.getBean("driver");
    }
}

