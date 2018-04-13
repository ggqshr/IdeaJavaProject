package Servlet;

import Enetity.Business;
import com.google.gson.Gson;
import dao.BusinessDao;
import impl.BusinessDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ggq on 2017/6/28.
 */
@WebServlet(name = "QueryAllBusiness", value = "/Servlet.QueryAllBusiness")
public class QueryAllBusiness extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        BusinessDao dao = new BusinessDaoImpl();
        Gson gson = new Gson();
        ArrayList<Business> businesses = null;
        String ss = null;
        if (method.equals("get")) {
            businesses = dao.listBusiness();
            ss = gson.toJson(businesses);
            response.getWriter().print(ss);
        } else if (method.equals("cust")) {
            String id = request.getParameter("id");
            businesses = dao.QueryBusinessAsCustNumber(id);
            ss = gson.toJson(businesses);
            response.getWriter().print(ss);
        } else if (method.equals("delete")) {
            String id = request.getParameter("id");
        }

    }
}
