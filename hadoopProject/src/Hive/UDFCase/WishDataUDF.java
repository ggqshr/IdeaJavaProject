package Hive.UDFCase;

import org.apache.hadoop.hive.ql.exec.UDF;

public class WishDataUDF extends UDF {
    public String evaluate(String line) {
        String line1 = line.replaceAll("\n", "");
        String line2 = line1.replaceAll("\r", "");
        String line3 = line2.replaceAll("\t", "");
        return line3;
    }
}
