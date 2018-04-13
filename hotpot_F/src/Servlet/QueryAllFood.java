package Servlet;

import Enetity.Food;
import com.google.gson.Gson;
import dao.FoodDao;
import impl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ggq on 2017/6/21.
 */
@WebServlet(name = "QueryAllFood",urlPatterns = "/Servlet.QueryAllFood")
public class QueryAllFood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        FoodDao dao = new FoodDaoImpl();
        Gson gson = new Gson();
        String foodType = request.getParameter("type");
        ArrayList<Food> foods = dao.queryFoodAsType(foodType);
        String ss = gson.toJson(foods);
        response.getWriter().print(ss);
    }
}
