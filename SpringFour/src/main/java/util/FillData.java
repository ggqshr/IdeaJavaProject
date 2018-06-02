package util;

import entity.Xcsessionchange;
import entity.Xctyperate;
import entity.Xcyearchange;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class FillData {
    public static void fillYearAndData(HashMap<String, String> map, String sql, Object[] para) {
        ArrayList<Xcyearchange> query = (ArrayList<Xcyearchange>) jdbcUtil.query(sql, para, new BeanListHandler(Xcyearchange.class));
        String years = "";
        String data = "";
        for (int i = 0; i < query.size() - 1; i++) {
            years = years + query.get(i).getComment_year() + "\t";
            data = data + query.get(i).getNum() + "\t";
        }
        years += query.get(query.size() - 1).getComment_year();
        data += query.get(query.size() - 1).getNum();
        map.put("years", years);
        map.put("data", data);
    }

    public static void fillSessAndData(HashMap<String, String> map, String sql, Object[] para) {
        ArrayList<Xcsessionchange> query = (ArrayList<Xcsessionchange>) jdbcUtil.query(sql, para, new BeanListHandler(Xcsessionchange.class));
        String sess = "";
        String data = "";
        sess = sess + query.get(0).getSessTime() + "\t";
        data = data + query.get(0).getSessnum() + "\t";
        sess = sess + query.get(2).getSessTime() + "\t";
        data = data + query.get(2).getSessnum() + "\t";
        sess = sess + query.get(1).getSessTime() + "\t";
        data = data + query.get(1).getSessnum() + "\t";
        sess += query.get(query.size() - 1).getSessTime();
        data += query.get(query.size() - 1).getSessnum();
        map.put("sess", sess);
        map.put("sessdata", data);
    }

    public static void fillTypeRate(HashMap<String, String> map, String sql, Object[] para) {
        ArrayList<Xctyperate> query = (ArrayList<Xctyperate>) jdbcUtil.query(sql, para, new BeanListHandler(Xctyperate.class));
        String type = "";
        String typenum = "";
        String typerate = "";
        for (int i = 0; i < query.size()-1; i++) {
            type+=query.get(i).getView_type()+"\t";
            typenum+=query.get(i).getAnum()+"\t";
            typerate+=(query.get(i).getPrate()*100)+"\t";
        }
        type+=query.get(query.size()-1).getView_type();
        typenum+=query.get(query.size()-1).getAnum();
        typerate+=(query.get(query.size()-1).getPrate()*100);
        map.put("type",type);
        map.put("typenum",typenum);
        map.put("typerate",typerate);
    }
}
