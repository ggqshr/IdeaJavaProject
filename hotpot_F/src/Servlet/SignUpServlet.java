package Servlet;

import Enetity.Customer;
import dao.CustomerDao;
import impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ggq on 2017/6/14.
 */
@WebServlet(name = "SignUpServlet", value = "/Servlet.SignUp")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String userNumber = request.getParameter("userNumber");
        String username = request.getParameter("signuserName");
        String pass = request.getParameter("signpassword");
        String phone = request.getParameter("Phone");
        String sex = "男";
        Customer customer ;
        CustomerDao dao = new CustomerDaoImpl();
        customer = dao.queryCustomerAsNumber(userNumber);
        if(customer==null)
        {
            customer = new Customer();
            customer.setCustomerNumber(userNumber);
            customer.setCustomerName(username);
            customer.setCustomerSex(sex);
            customer.setCustomerPass(pass);
            customer.setCustomerPhone(phone);
            dao.insertCustomer(customer);
        }
        else
        {
            out.print("exist");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
