import Pojo.User;
import Util.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
public class DbTESt {

    @Before
    public void cc() {
    }

    @Test
    public void tt() {
        //Spring的思想是由Spring来托管所有对象的创建和产生，Spring将其创建的对象都放在Context中，所以在使用之前
        //必须创建一个Context，才能够使用在Spring容器中创建的类。
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/Context.xml");
        JdbcUtil jdbcUtil = (JdbcUtil) applicationContext.getBean("jdbcutl");
        List list = jdbcUtil.queryMany("select * from user", new Object[]{}, new BeanListHandler(User.class));
        ArrayList<User> ll = (ArrayList<User>) list;
        for (User user : ll) {
            System.out.println(user.getUserName());
        }
    }
}
