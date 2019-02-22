package CustomerUdf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractLog extends UDF {
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

    private boolean isDecodeField(String thisField) {
        return thisField.equals("title")
                || thisField.equals("referrerUrl")
                || thisField.equals("currentUrl")
                || thisField.equals("userAgent");
    }

    public ArrayList<String> converline2field(String line){

        Matcher matcher = pattern.matcher(line);
        ArrayList<String> mm = new ArrayList<>();


        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                try {
                    String thisField = fieldName[i];
                    String thisGroup = matcher.group(thisField);

                    if (isDecodeField(thisField)) {
                        thisGroup = URLDecoder.decode(thisGroup, "utf-8");
                    }
                    mm.add(thisGroup);
                } catch (IllegalArgumentException e) {
                    mm.add("ggq");
                }
                catch (UnsupportedEncodingException e) {
                    mm = new ArrayList<>();
                    return mm;
                }
            }

        }
        return mm;
    }

    public ArrayList<String> evaluate(String line) throws UnsupportedEncodingException {
        String[] split = line.split(" ");
        ArrayList<String> fields = new ArrayList<>();
        if("-".equals(split[1])){
            return fields;
        }
        fields.addAll(Arrays.asList(Arrays.copyOfRange(split,0,3)));
        fields.addAll(converline2field(split[3]));
        return fields;
    }
}
