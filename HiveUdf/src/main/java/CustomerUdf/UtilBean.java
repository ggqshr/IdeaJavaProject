package CustomerUdf;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilBean {
    static private Pattern pattern = Pattern.compile("en=(?<eventName>.+?)&" +
            "ver=(?<version>\\d+)&" +
            "pl=(?<platform>.+?)&" +
            "sdk=(?<sdk>.+?)&" +
            "uid=(?<uuid>.+?)&" +
            "sid=(?<sessionId>.+?)&" +
            "b_rst=(?<resolution>.+?)&" +
            "b_usa=(?<userAgent>.+?)&" +
            "l=(?<language>.+?)&" +
            "ct=(?<clientTime>.+?)&" +
            "url=(?<currentUrl>.+?)&" +
            "ref=(?<referrerUrl>.+?)&" +
            "tt=(?<title>.+?)$");
    static private String[] fieldName = {
            "eventName",
            "version",
            "platform",
            "sdk",
            "uuid",
            "sessionId",
            "memberId",
            "resolution",
            "userAgent",
            "language",
            "clientTime",
            "currentUrl",
            "referrerUrl",
            "title"
    };
    static private String[] customerList = {
            "ggq", "dps", "lol", "ttt"
    };
    static private Random random = new Random();

    static private boolean isDecodeField(String thisField) {
        return thisField.equals("title")
                || thisField.equals("referrerUrl")
                || thisField.equals("currentUrl")
                || thisField.equals("userAgent");
    }

    static public HashMap<String, String> converline2field(String line) {

        Matcher matcher = pattern.matcher(line);
        HashMap<String, String> map = new HashMap<>();

        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount() + 1; ++i) {
                String thisField = fieldName[i];

                try {
                    String thisGroup = matcher.group(thisField);
                    if (isDecodeField(thisField)) {
                        thisGroup = URLDecoder.decode(thisGroup, "utf-8");
                    } else if ("clientTime".equals(thisField)) {
                        thisGroup = UtilBean.TimeStamp2Date(thisGroup, "yyyy-MM-dd HH:mm:ss:SSS");
                    }
                    map.put(thisField, thisGroup);
                } catch (IllegalArgumentException e) {
                    //随机一个客户
                    String Group = customerList[random.nextInt(customerList.length)];
                    map.put(thisField, Group);

                } catch (UnsupportedEncodingException e) {
                    map.put(thisField,"null");
                } catch (Exception e) {
                    map.clear();
                    return map;
                }
            }

        }
        return map;
    }

    static public String TimeStamp2Date(String timestampString, String formats) {
        long timestamp = Long.parseLong(timestampString);
        String format = new SimpleDateFormat(formats).format(new Date(timestamp));
        return format;
    }

    static public String extractDomain(String url) {
        try {
            URL uu = new URL(url);
            return uu.getHost();
        } catch (MalformedURLException e) {
            return "null";
        }

    }
}
