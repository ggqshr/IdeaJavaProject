package Dao;

import Pojo.Customer;

import java.util.List;

public interface CustomerDao {
    /**
     * 按照手机号查询顾客
     * @param phone 顾客的姓名
     * @return 返回一个实例
     */
    Customer searchCustomerByPhoneNumber(String phone);

    /**
     * 按照姓名查询客户
     * @param name 顾客的姓名
     * @return 返回一个实例
     */
    Customer searchCustomerByName(String name);

    /**
     * 添加一个客户
     * @param customer 客户的实例，分装了客户信息
     * @return 返回受影响的行数
     */
    int addCustomer(Customer customer);

    /**
     * 按照手机号删除客户
     * @param phone 顾客的手机号
     * @return 返回受影响的行数
     */
    int deleteCustomer(String phone);

    /**
     * 按照手机号和姓名更新顾客的信息
     * @param customer 封装顾客的手机号
     * @return 返回受影响的行数
     */
    int updateByPhoneOrName(Customer customer);

    /**
     * 得到所有的顾客信息
     * @return 所有顾客信息的列表
     */
    List<Customer> getAllCustomer();
}
