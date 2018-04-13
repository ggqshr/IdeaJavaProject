package Servlet;

import Enetity.Food;
import dao.FoodDao;
import impl.FoodDaoImpl;
import org.apache.commons.fileupload.FileItem;
import util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggq on 2017/6/21.
 */
@WebServlet(name = "FoodServlet", value = "/Servlet.Food")
public class FoodServlet extends HttpServlet {
    private static String uppath = "assets" + File.separator + "images" + File.separator + "food";
    private FoodDao dao = new FoodDaoImpl();
    private String p;
    private File uploadPath;
    private Tool tool = new Tool();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        p = getServletContext().getRealPath("") + File.separator + uppath;
        uploadPath = new File(p);
        boolean flag = false;
        FileItem upLoadFileItem = null;
        Map param = new HashMap();
        String method = "";

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        List items = tool.readIo(request);

        //将请求中的表单数据存放到map中
        for (Object object : items) {
            FileItem fileItem = (FileItem) object;
            if (fileItem.isFormField()) {
                param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
            } else {
                upLoadFileItem = fileItem;
                flag = true;
            }
        }

        Food food = dao.queryFoodAsName((String) param.get("foodName"));
        if (food != null && !food.getFoodNumber().equals(param.get("foodNumber"))) {
            System.out.print("@@@");
            out.print("foodfound");
            return;
        }

        method = (String) param.get("method");
        if (method.equals("Update")) {
            updateFood(param);
            if (flag && !upLoadFileItem.getName().equals("")) {
                tool.saveFile(upLoadFileItem, (String) param.get("foodNumber"), uploadPath);
            }
        } else if (method.equals("Add")) {
            if (flag && !upLoadFileItem.getName().equals("")) {
                String num = addFood(param);
                tool.saveFile(upLoadFileItem, num, uploadPath);
            } else {
                out.print("notloadfile");
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        p = getServletContext().getRealPath("") + File.separator + uppath;
        uploadPath = new File(p);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        FoodDao dao = new FoodDaoImpl();
        Food food = null;
        String method = request.getParameter("method");
        if (method.equals("getFood")) {
            String foodNumber = request.getParameter("id");
            food = dao.queryFoodAsNumber(foodNumber);
            session.setAttribute("food", food);
            request.getRequestDispatcher("/html/FoodInf.jsp").forward(request, response);
        } else if (method.equals("Delete")) {
            String foodNumber = request.getParameter("id");
            dao.deleteFoodAsNumber(foodNumber);
            String filePath = uploadPath + File.separator + foodNumber + ".jpg";
            System.out.print(filePath);
            File file = new File(filePath);
            file.delete();
            out.print("ok");
        }
    }

    //更新食物
    private void updateFood(Map param) {
        Food food = dao.queryFoodAsNumber((String) param.get("foodNumber"));
        food.setFoodName((String) param.get("foodName"));
        food.setFoodPrice(Float.valueOf((String) param.get("foodPrice")));
        dao.updateFood(food);
    }


    //添加食物
    private String addFood(Map param) {
        Food food = new Food();
        food.setFoodName((String) param.get("foodName"));
        food.setFoodPrice(Float.valueOf((String) param.get("foodPrice")));
        food.setFoodType((String) param.get("foodType"));
        return dao.insertFood(food);
    }
}
