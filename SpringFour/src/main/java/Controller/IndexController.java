package Controller;

import com.google.gson.Gson;
import com.sun.tracing.dtrace.Attributes;
import entity.Xcscorerank;
import entity.all_rank_table;
import entity.xcMostKey;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.jdbcUtil;

import java.util.List;

@Controller
public class IndexController {
    Gson gson = new Gson();

    //    @RequestMapping(value = "/testc", method = RequestMethod.GET)
    public String index(ModelMap model) {
        System.out.println("@@@");
        model.addAttribute("greetion", "hello world Spring 4");
        return "index";
    }

    @RequestMapping(value = "/testc", method = RequestMethod.GET)
    public String getPlaceJson(ModelMap mm) {
        String sql = "select * from all_rank_table";
        List result = jdbcUtil.query(sql, null, new BeanListHandler(all_rank_table.class));
        mm.addAttribute("list", result);
        return "index";
    }

    @RequestMapping(value = "/place")
    public String getPlacePage( String p, ModelMap mm) {
        mm.addAttribute("comment_place",p);
        return "PlacePage";
    }
}
