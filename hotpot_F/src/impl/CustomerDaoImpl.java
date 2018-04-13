package impl;

import Enetity.Customer;
import dao.CustomerDao;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDaoImpl extends BaseDao implements CustomerDao {

    @Override
    public void insertCustomer(Customer customer) {
        // TODO Auto-generated method stub
        String sql = " insert into customer values(?,?,?,?,?)";
        Object[] params = {
                customer.getCustomerNumber(), customer.getCustomerPass(),
                customer.getCustomerName(), customer.getCustomerSex(),
                customer.getCustomerPhone()
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        // TODO Auto-generated method stub
        String sql = " update customer set customerPass=?,"
                + " customerName=?, customerPhone=?"
                + " where customerNumber= ?";
        Object[] parama = {
                customer.getCustomerPass(), customer.getCustomerName(),
                customer.getCustomerPhone(), customer.getCustomerNumber()
        };
        try {
            util.executeUpdate(sql, parama);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomerPass(Customer customer) {
        // TODO Auto-generated method stub
        String sql = " update customer set customerPass=? where customerNumber = ?";
        Object[] parama = {
                customer.getCustomerPass(), customer.getCustomerNumber()
        };
        try {
            util.executeUpdate(sql, parama);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Customer queryCustomerAsNumber(String customerNumber) {
        // TODO Auto-generated method stub
        Customer customer = null;
        ResultSet resultSet = null;
        String sql = " select * from customer where customerNumber = ?";
        Object[] params = {
                customerNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerNumber(resultSet.getString("customerNumber"));
                customer.setCustomerPass(resultSet.getString("customerPass"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setCustomerSex(resultSet.getString("customerSex"));
                customer.setCustomerPhone(resultSet.getString("customerPhone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer quertCustomerAsName(String customerName) {
        // TODO Auto-generated method stub
        String sql = " select * from customer where customerName=?";
        Customer customer = null;
        ResultSet resultSet = null;
        Object[] params = {
                customerName
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerNumber(resultSet.getString("customerNumber"));
                customer.setCustomerPass(resultSet.getString("customerPass"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setCustomerSex(resultSet.getString("customerSex"));
                customer.setCustomerPhone(resultSet.getString("customerPhone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return customer;
    }

}
