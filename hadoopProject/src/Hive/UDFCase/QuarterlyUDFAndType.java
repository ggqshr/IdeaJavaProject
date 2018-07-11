package Hive.UDFCase;

import Utils.PartitionSesssionUtil;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * #  每个季度 每种出行方式的数量，
 */
public class QuarterlyUDFAndType extends UDF {
    public String evaluate(String data) {
        try{
            String[] split = data.split("-");
            return PartitionSesssionUtil.partitionSesssion(split[1]);
        }   catch (Exception e)
        {
            return null;
        }
    }
}
