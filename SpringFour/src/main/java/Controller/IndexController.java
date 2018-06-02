package Controller;

import com.google.gson.Gson;
import com.sun.tracing.dtrace.Attributes;
import entity.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.FillData;
import util.jdbcUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {
    Gson gson = new Gson();

    @RequestMapping(value = "/getPlace", method = RequestMethod.GET)
    public String getPlace(String place, ModelMap mm) {
        Object[] para = {
                place
        };
        String rankSql = "select * from xcscorerank where comment_place=?";
        List query = jdbcUtil.query(rankSql, para, new BeanListHandler(Xcscorerank.class));
        if (query.size() == 0) {
            String testSql = "select * from xcemotionandkey where comment_place=?";
            List query1 = jdbcUtil.query(testSql, para, new BeanListHandler(Xcemotionandkey.class));
            if (query1.size() == 0) {
                return "error-404";
            } else {
                return getPlacePage(place, "无", "无", "无", "无", mm);
            }
        }
        ArrayList<Xcscorerank> list = (ArrayList<Xcscorerank>) query;
        return getPlacePage(place, String.valueOf(list.get(0).getView_rank()),
                String.valueOf(list.get(0).getPrice_rank()),
                String.valueOf(list.get(0).getInterest_rank()),
                String.valueOf(list.get(0).getTotal_rank()), mm);
    }

    @RequestMapping(value = "/searchPlace")
    public String index(ModelMap model) {
        return "searchPage";
    }

    @RequestMapping(value = "/allPlace")
    public String getallPlaceData(ModelMap mm) {
        String yearSql = "select comment_year,sum(num)as num from xcyearchange group by comment_year";
        String sessSql = "select sessTime,sum(sessnum)as sessnum from xcsessionchange group by  sessTime order by sessTime";
        String typeSql = "select view_type,sum(pnum)/sum(anum)as prate , sum(anum) as anum from xctyperate group by view_type";
        HashMap<String, String> map = new HashMap<>();
        FillData.fillYearAndData(map, yearSql, null);
        FillData.fillSessAndData(map, sessSql, null);
        FillData.fillTypeRate(map, typeSql, null);
        mm.addAttribute("yeardata", map.get("data"));
        mm.addAttribute("yy", map.get("years"));
        mm.addAttribute("sessdata", map.get("sessdata"));
        mm.addAttribute("sess", map.get("sess"));
        mm.addAttribute("typerate", map.get("typerate"));
        mm.addAttribute("type", map.get("type"));
        mm.addAttribute("typenum", map.get("typenum"));
        return "allPlace";
    }

    @RequestMapping(value = "/testc", method = RequestMethod.GET)
    public String getPlaceJson(ModelMap mm) {
        String sql = "select * from all_rank_table";
        List result = jdbcUtil.query(sql, null, new BeanListHandler(all_rank_table.class));
        mm.addAttribute("list", result);
        return "index";
    }

    @RequestMapping(value = "/place")
    public String getPlacePage(String p, String view, String price, String interest, String total, ModelMap mm) {
        Object[] para = {
                p
        };
        String sql = "select * from xcyearchange where comment_place=?";
        String sessSql = "select * from xcsessionchange where comment_place = ? order by sessTime";
        String typeSql = "select * from xctyperate where comment_place=?";
        String rankSql = "select * from xcscorerank where comment_place=?";
        String summarySqlP = "select * from xcemotionandkey where comment_place=? and emotion=\"正面\" order by useful desc";
        String summarySqlN = "select * from xcemotionandkey where comment_place=? and emotion=\"负面\" order by useful desc";
        List query = jdbcUtil.query(rankSql, para, new BeanListHandler(Xcscorerank.class));
        List pTag = jdbcUtil.query(summarySqlP, para, new BeanListHandler(Xcemotionandkey.class));
        List nTag = jdbcUtil.query(summarySqlN, para, new BeanListHandler(Xcemotionandkey.class));
        HashMap<String, String> map = new HashMap<>();
        FillData.fillYearAndData(map, sql, para);
        FillData.fillSessAndData(map, sessSql, para);
        FillData.fillTypeRate(map, typeSql, para);
        for (int i=0;i<3-pTag.size();i++)
        {
            Xcemotionandkey xx = new Xcemotionandkey();
            xx.setTag("无");
            pTag.add(xx);
        }
        for (int i=0;i<3-nTag.size();i++)
        {
            Xcemotionandkey xx = new Xcemotionandkey();
            xx.setTag("无");
            nTag.add(xx);
        }
        mm.addAttribute("pTag", (ArrayList<Xcemotionandkey>) pTag);
        mm.addAttribute("nTag", (ArrayList<Xcemotionandkey>) nTag);
        Xcscorerank ss = null;
        if (view.equals("无")) {
            ss = new Xcscorerank();
            ss.setComment_place(p);
            ss.setInterest_rank(0.0);
            ss.setPrice_rank(0.0);
            ss.setTotal_rank(0.0);
            ss.setView_rank(0.0);
        } else {
            ss = (Xcscorerank) query.get(0);
        }
        mm.addAttribute("score", ss);
        mm.addAttribute("comment_place", p);
        mm.addAttribute("view_rank", view);
        mm.addAttribute("price_rank", price);
        mm.addAttribute("interest_rank", interest);
        mm.addAttribute("total_rank", total);
        mm.addAttribute("yy", map.get("years"));
        mm.addAttribute("yeardata", map.get("data"));
        mm.addAttribute("sess", map.get("sess"));
        mm.addAttribute("sessdata", map.get("sessdata"));
        mm.addAttribute("type", map.get("type"));
        mm.addAttribute("typenum", map.get("typenum"));
        mm.addAttribute("typerate", map.get("typerate"));
        return "PlacePage";
    }
}
