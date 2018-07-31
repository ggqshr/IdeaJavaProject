package Service;

import Pojo.User;

import java.util.HashMap;

public interface UserService {
    /**
     * 用户登陆
     *
     * @param user 传入的用户实例，封装了用户名和密码
     * @return 返回一个完整的用户实例
     */
    HashMap<String,Object> userLogin(User user);
}
