package CustomerUdf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class One2More extends UDF {
    public HashMap<String, String> evaluate(String line) throws UnsupportedEncodingException {
//        String[] split = line.split("\t");
//        ArrayList<String> ss = new ArrayList<>(Arrays.asList(split));
        HashMap<String, String> mm = new HashMap<>();
        mm.put("hello", "sss");
        mm.put("this is ", "test");
        return mm;
    }
}
