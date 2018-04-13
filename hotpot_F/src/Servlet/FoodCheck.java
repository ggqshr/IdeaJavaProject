package Servlet;

import com.google.gson.Gson;
import dao.FoodCheckDao;
import impl.FoodCheckDaoImpl;

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
@WebServlet(name = "FoodCheck",value = "/Servlet.FoodCheck")
public class FoodCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String businessNumber = request.getParameter("id");
        FoodCheckDao dao = new FoodCheckDaoImpl();
        ArrayList<Enetity.FoodCheck> foodChecks = dao.getPlusFoodCheck(businessNumber);
        Gson gson = new Gson();
        String ss = gson.toJson(foodChecks);
        response.getWriter().print(ss);
    }
}
