package CustomerUdf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class One2More extends UDF {
    public ArrayList<String> evaluate(String line) throws UnsupportedEncodingException {
        String[] split = line.split("\t");
        ArrayList<String> ss = new ArrayList<>(Arrays.asList(split));
        return ss;
    }
}
