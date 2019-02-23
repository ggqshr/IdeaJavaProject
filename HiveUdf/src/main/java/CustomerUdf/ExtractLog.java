package CustomerUdf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static CustomerUdf.UtilBean.extractDomain;

public class ExtractLog extends UDF {
    //"yyyy-MM-dd HH:mm:ss:SSS"
    private String getDateFormat = "yyyy-MM-dd";
    private String getTime = "HH:mm:ss:SSS";
    private String getHour = "HH";


    public HashMap<String, String> evaluate(String line) {
        String[] split = line.split(" ");

        HashMap<String, String> fields = new HashMap<>();
        if ("-".equals(split[1])) {
            return fields;
        }
        // 获取时间戳
        String timestampString = split[1].replace(".", "");
        long timestamp = Long.parseLong(timestampString);

        //将时间戳格式化
        split[1] = UtilBean.TimeStamp2Date(timestampString, "yyyy-MM-dd HH:mm:ss:SSS");

        fields.put("originIp", split[0]);
        fields.put("requestFullTime", split[1]);
        fields.put("requestIp", split[2]);
        fields.putAll(UtilBean.converline2field(split[3]));

        //添加日期的三个字段
        fields.put("accessDate", new SimpleDateFormat(getDateFormat).format(new Date(timestamp)));
        fields.put("accessTime", new SimpleDateFormat(getTime).format(new Date(timestamp)));
        fields.put("accessHour", new SimpleDateFormat(getHour).format(new Date(timestamp)));

        //提取域名

        fields.put("referDomain", extractDomain(fields.get("referrerUrl")));

        return fields;
    }
}
