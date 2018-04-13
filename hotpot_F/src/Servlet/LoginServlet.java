package Servlet;

import Enetity.Customer;
import dao.CustomerDao;
import impl.CustomerDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ggq on 2017/6/10.
 */
@WebServlet(name = "Servlet.LoginServlet", value = "/Servlet.LoginServlet", displayName = "/")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String passwrod = request.getParameter("password");
        String autoLogin = request.getParameter("autologin");
        Customer customer = null;
        CustomerDao dao = new CustomerDaoImpl();
        HttpSession session = request.getSession();
        customer = dao.queryCustomerAsNumber(username);
        if (customer == null) {
            out.print("exist");
        } else {
            if (passwrod.equals(customer.getCustomerPass())) {
                if(autoLogin!=null&&autoLogin.equals("on"))
                {
                    Cookie cookie = new Cookie("JSESSIONID",session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(24*60*60);
                    response.addCookie(cookie);
                }
                session.setAttribute("username", customer.getCustomerName());
                request.getRequestDispatcher("/ajax.jsp").forward(request, response);
            } else {
                out.print("error");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}