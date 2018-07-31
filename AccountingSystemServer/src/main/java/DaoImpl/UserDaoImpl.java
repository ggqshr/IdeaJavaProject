package DaoImpl;

import Dao.UserDao;
import Pojo.User;
import Util.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository(value = "UserDaoImpl")
public class UserDaoImpl implements UserDao {
    @Resource(name = "JdbcUtil")
    private JdbcUtil jdbcUtil;

    @Override
    public int addUser(User user) {
        String sql = "insert into user values(?,?,?,?)";
        Object[] para = {
                user.getUserId(),
                user.getUserPassword(),
                user.getUserName(),
                user.getUserLevel()
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public int deleteUserByUserId(String userId) {
        String sql = "delete from user where userId=?";
        Object[] para = {
                userId
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public int deleteUserByName(String userName) {
        String sql = "delete from user where userName=?";
        Object[] para = {
                userName
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public User searchUserById(String userId) {
        String sql = "select * from user where userId = ?";
        Object[] para = {
                userId
        };
        return (User) jdbcUtil.queryOne(sql, para, new BeanHandler(User.class));
    }

    @Override
    public User searchUserByName(String name) {
        String sql = "select * from user where userName = ?";
        Object[] para = {
                name
        };
        return (User) jdbcUtil.queryOne(sql, para, new BeanHandler(User.class));
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select * from user";
        Object[] para = {};
        return jdbcUtil.queryMany(sql, para, new BeanListHandler(User.class));
    }
}
