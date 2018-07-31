package Dao;

import Pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 添加一个收银员
     *
     * @param user 传入一个user实例
     * @return 返回受影响的行数
     */
    int addUser(User user);

    /**
     * 通过ID删除一个收银员
     *
     * @param userId 收银员的ID
     * @return 返回受影响的函数
     */
    int deleteUserByUserId(String userId);

    /**
     * 通过name删除一个收银员
     *
     * @param userName 传入的姓名
     * @return 受影响的函数
     */
    int deleteUserByName(String userName);

    /**
     * 通过userId查询收银员
     *
     * @param userId 传入Id
     * @return user对象
     */
    User searchUserById(String userId);

    /**
     * 通过name查询收银员
     *
     * @param name 收银员姓名
     * @return 收银员实例
     */
    User searchUserByName(String name);

    /**
     * 获得所有的收银员
     *
     * @return 收银员列表
     */
    List<User> getAllUser();

}
