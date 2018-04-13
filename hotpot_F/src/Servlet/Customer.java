package Servlet;

import dao.CustomerDao;
import impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ggq on 2017/6/28.
 */
@WebServlet(name = "Customer", value = "/Servlet.Customer")
public class Customer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        CustomerDao dao = new CustomerDaoImpl();
        if (method.equals("get")) {
            String id = request.getParameter("id");
            Enetity.Customer customer = dao.queryCustomerAsNumber(id);
            if (customer == null) {
                out.print("NULL");
            }
        }
        else if(method.equals("updata"))
        {
            String id = request.getParameter("id");
            String newpwd = request.getParameter("newpwd");
            Enetity.Customer customer = dao.queryCustomerAsNumber(id);
            customer.setCustomerPass(newpwd);
            dao.updateCustomer(customer);
        }
    }
}
