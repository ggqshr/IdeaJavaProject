package ServiceImpl;

import Dao.UserDao;
import Pojo.User;
import Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Resource(name = "UserDaoImpl")
    private UserDao userDao;

    @Override
    public HashMap<String, Object> userLogin(User user) {
        HashMap<String, Object> map = new HashMap<>();
        String userId = user.getUserId();
        String pwd = user.getUserPassword();
        System.out.println(userId);
        System.out.println(pwd);
        User user1 = userDao.searchUserById(userId);
        if (user1 == null) {
            map.put("info", "用户不存在");
            return map;
        } else {
            if (!pwd.equals(user1.getUserPassword())) {
                map.put("info", "密码不正确");
                return map;
            } else {
                map.put("info", "登陆成功");
                map.put("userPojo", user1);
                return map;
            }
        }
    }
}
