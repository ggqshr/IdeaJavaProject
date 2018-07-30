package DaoImpl;

import Dao.CustomerDao;
import Pojo.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoImplTest {
    private ApplicationContext applicationContext;
    CustomerDao customerDaoImpl;

    @Before
    public void init() {
        applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/Context.xml");
        customerDaoImpl = (CustomerDao) applicationContext.getBean("CustomerDaoImpl");
    }

    @Test
    public void searchCustomerByPhoneNumber() {
        Customer customer = customerDaoImpl.searchCustomerByPhoneNumber("123456");
        System.out.println(customer.getCustomerName());
    }

    @Test
    public void searchCustomerByName() {
        Customer customer = customerDaoImpl.searchCustomerByName("1");
        System.out.println(customer.getCustomerName());
    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setCustomerAdress("2");
        customer.setCustomerName("ggq");
        customer.setCustomerPhoneNumber("222");
        customer.setCustomerRemarks("111");
        System.out.println(customerDaoImpl.addCustomer(customer));
    }

    @Test
    public void deleteCustomer() {
        System.out.println(customerDaoImpl.deleteCustomer("222"));
    }

    @Test
    public void updateByPhoneOrName() {
        Customer customer = new Customer();
        customer.setCustomerAdress("tz");
        customer.setCustomerName("ggq");
        customer.setCustomerPhoneNumber("222");
        customer.setCustomerRemarks("111");
        System.out.println(customerDaoImpl.updateByPhoneOrName(customer));
    }

    @Test
    public void getAllCustomer() {
        List allCustomer = customerDaoImpl.getAllCustomer();
        ArrayList<Customer> arr = (ArrayList<Customer>) allCustomer;
        for (Customer customer : arr) {
            System.out.println(customer.getCustomerName());
        }
    }
}