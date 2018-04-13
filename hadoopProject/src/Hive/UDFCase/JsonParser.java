package Hive.UDFCase;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonParser extends UDF {
    public String evaluate(String line) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            MovieBean movieBean = mapper.readValue(line, MovieBean.class);
            return movieBean.toString();
        } catch (Exception e) {

        }
        return "";
    }
}
