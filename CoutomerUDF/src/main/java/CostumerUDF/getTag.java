package CostumerUDF;

import com.hankcs.hanlp.HanLP;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class getTag extends UDF {
    public static String changeCodeToUtf8(String origin) throws UnsupportedEncodingException {
        return new String(origin.getBytes("utf-8"),"utf-8");
    }
    public String evaluate(String line) throws UnsupportedEncodingException {
        List<String> strings = HanLP.extractSummary(changeCodeToUtf8(line), 100);
        return String.join(",", strings);
    }
}
