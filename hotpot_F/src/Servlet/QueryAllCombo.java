package Servlet;

import Enetity.Combo;
import com.google.gson.Gson;
import dao.ComboDao;
import impl.ComboDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ggq on 2017/6/16.
 */
@WebServlet(name = "QueryAllCombo", value = "/Servlet.QueryAllCombo")
public class QueryAllCombo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ComboDao dao = new ComboDaoImpl();
        Gson gson = new Gson();
        ArrayList<Combo> combos = dao.listCombo();
        String ss = gson.toJson(combos);
        response.getWriter().print(ss);
    }
}
