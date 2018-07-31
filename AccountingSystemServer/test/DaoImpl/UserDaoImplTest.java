package DaoImpl;

import Dao.CustomerDao;
import Dao.UserDao;
import Pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private ApplicationContext applicationContext;
    UserDao userDao;

    @Before
    public void init() {
        applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/Context.xml");
        userDao = (UserDao) applicationContext.getBean("UserDaoImpl");
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUserId("1");
        user.setUserLevel("ww");
        user.setUserName("ggq");
        user.setUserPassword("123");
        System.out.println(userDao.addUser(user));
    }

    @Test
    public void deleteUserByUserId() {
        System.out.println(userDao.deleteUserByUserId("1"));
    }

    @Test
    public void deleteUserByName() {
        System.out.println(userDao.deleteUserByName("ggq"));
    }

    @Test
    public void searchUserById() {
        System.out.println(userDao.searchUserById("1"));
    }

    @Test
    public void searchUserByName() {
        System.out.println(userDao.searchUserByName("ggq"));
    }

    @Test
    public void getAllUser() {
        List<User> allUser = userDao.getAllUser();
        for (User user : allUser) {
            System.out.println(user.getUserName());
        }
    }
}