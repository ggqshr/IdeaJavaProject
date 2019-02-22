package CustomerUdf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.ArrayList;
import java.util.Arrays;

public class getFieldName extends UDF {
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
    public ArrayList<String> evaluate(){
        return new ArrayList<String>(Arrays.asList(fieldName));
    }
}
