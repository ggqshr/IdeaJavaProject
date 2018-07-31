package DaoImpl;

import Dao.CustomerDao;
import Pojo.Customer;
import Util.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.List;

@Repository(value = "CustomerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {
    @Resource(name = "JdbcUtil")
    private JdbcUtil jdbcUtil;

    @Override
    public Customer searchCustomerByPhoneNumber(String phone) {
        String sql = " select * from customer where customerPhoneNumber = ?";
        Object[] para = {
                phone
        };
        return (Customer) jdbcUtil.queryOne(sql, para, new BeanHandler(Customer.class));
    }

    @Override
    public Customer searchCustomerByName(String name) {
        String sql = " select * from customer where customerName = ?";
        Object[] para = {
                name
        };
        return (Customer) jdbcUtil.queryOne(sql, para, new BeanHandler(Customer.class));
    }

    @Override
    public int addCustomer(Customer customer) {
        String sql = "insert into customer values(?,?,?,?)";
        Object[] para = {
                customer.getCustomerPhoneNumber(), customer.getCustomerName(),
                customer.getCustomerAdress(), customer.getCustomerRemarks()
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public int deleteCustomer(String phone) {
        String sql = "delete from customer where customerPhoneNumber = ?";
        Object[] para = {
                phone
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public int updateByPhoneOrName(Customer customer) {
        String sql = "update customer set customerAdress=?,customerRemarks=? " +
                " where customerPhoneNumber=? or customerName=?";
        Object[] para = {
                customer.getCustomerAdress(),
                customer.getCustomerRemarks(),
                customer.getCustomerPhoneNumber(),
                customer.getCustomerName()
        };
        return jdbcUtil.executeSql(sql, para);
    }

    @Override
    public List<Customer> getAllCustomer() {
        String sql = "select * from customer";
        Object[] para = {};
        return jdbcUtil.queryMany(sql, para, new BeanListHandler(Customer.class));
    }
}
