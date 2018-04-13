package Servlet;

import Enetity.Combo;
import dao.ComboDao;
import impl.ComboDaoImpl;
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
 * Created by ggq on 2017/6/17.
 */
@WebServlet(name = "ComboServlet", value = "/Servlet.Combo")
public class ComboServlet extends HttpServlet {
    private static String uppath = "assets" + File.separator + "images" + File.separator + "combo";
    private ComboDao dao = new ComboDaoImpl();
    private String p;
    private File uploadPath;
    private Tool tool = new Tool();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置文件上传后存放在服务器上的路径
        p = getServletContext().getRealPath("") + File.separator + uppath;
        uploadPath = new File(p);
        boolean flag = false;
        FileItem upLoadFileItem = null;
        Map param = new HashMap();
        String method = "";

        //初始化上传参数
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //解析request流
        List items = tool.readIo(request);

        //将请求中的表单数据存放到map中
        for (Object object : items) {
            FileItem fileItem = (FileItem) object;
            if (fileItem.isFormField()) {
                param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
            } else {
                flag = true;
                upLoadFileItem = fileItem;
            }
        }

        Combo combo = dao.queryComboAsInf((String) param.get("comboName"));
        if (combo != null && !combo.getComboNumber().equals(param.get("comboNumber"))) {
            out.print("combofound");
            return;
        }


        method = (String) param.get("method");
        if (method.equals("Update")) {
            updateCombo(param);
            if (flag && !upLoadFileItem.getName().equals("")) {
                tool.saveFile(upLoadFileItem, (String) param.get("comboNumber"), uploadPath);
            }
        } else if (method.equals("Add")) {
            if (flag && !upLoadFileItem.getName().equals("")) {
                String num = addCombo(param);
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
        ComboDao dao = new ComboDaoImpl();
        Combo combo = null;
        String method = request.getParameter("method");
        if (method.equals("getCombo")) {
            String comboNumber = request.getParameter("id");
            combo = dao.queryComboAsNumber(comboNumber);
            session.setAttribute("combo", combo);
            request.getRequestDispatcher("/html/ComboInf.jsp").forward(request, response);
        } else if (method.equals("Delete")) {
            String comboNumber = request.getParameter("id");
            dao.deleteCombo(comboNumber);
            String filePath = uploadPath + File.separator + comboNumber + ".jpg";
            System.out.println(filePath);
            File file = new File(filePath);
            file.delete();
            out.print("ok");
        }
    }

    //更新套餐
    private void updateCombo(Map param) {
        Combo combo1 = dao.queryComboAsNumber((String) param.get("comboNumber"));
        combo1.setComboName((String) param.get("comboName"));
        combo1.setComboPrice(Float.valueOf((String) param.get("comboPrice")));
        combo1.setHotpotSoup((String) param.get("comboSoup"));
        combo1.setVegetableNum(Integer.valueOf(param.get("vegetableNum").toString()));
        combo1.setMeatNum(Integer.valueOf(param.get("meatNum").toString()));
        combo1.setNoodlesNum(Integer.valueOf(param.get("noodleNum").toString()));
        combo1.setSoupNum(Integer.valueOf(param.get("soupNum").toString()));
        dao.updateCombo(combo1);
    }

    //增加套餐
    private String addCombo(Map param) {
        Combo combo1 = new Combo();
        combo1.setComboName((String) param.get("comboName"));
        combo1.setComboPrice(Float.valueOf((String) param.get("comboPrice")));
        combo1.setHotpotSoup((String) param.get("comboSoup"));
        combo1.setVegetableNum(Integer.valueOf(param.get("vegetableNum").toString()));
        combo1.setMeatNum(Integer.valueOf(param.get("meatNum").toString()));
        combo1.setNoodlesNum(Integer.valueOf(param.get("noodleNum").toString()));
        combo1.setSoupNum(Integer.valueOf(param.get("soupNum").toString()));
        return dao.insertCombo(combo1);
    }
}
