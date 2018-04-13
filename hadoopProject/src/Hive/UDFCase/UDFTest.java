package Hive.UDFCase;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.HashMap;

/**
 * 自定义hive函数
 * 打成jar包后，传到服务器目录下，
 * 在hive中使用 add jar /home/hadoop/UDFTest.jar;
 * 然后使用 create temporary function 函数名 as '类的完整路径';
 */
public class UDFTest extends UDF {
    private static HashMap<String, String> provinceMap = new HashMap<>();

    static {
        provinceMap.put("136", "北京");
        provinceMap.put("137", "上海");
        provinceMap.put("138", "日本");
        provinceMap.put("139", "山东");
    }

    //函数名字别写错，要注意是public
    public String evaluate(String phone) {
        return provinceMap.get(phone.substring(0, 3)) == null ? "火星" : provinceMap.get(((String) phone).substring(0, 3));
    }

}
