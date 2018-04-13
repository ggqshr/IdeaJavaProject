package bigdata.provinceflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * k2 v2 对应的是map输出kv的类型
 * Partitioner就相当于确定map后的数据属于哪一个部分，
 * 通过提供编号，相同编号的Key就会被放到同一个文件中
 * 而默认的HashPartitioner是用hascode与一个数字相&，然后除以reducer的数量，
 * 而reducer的数量是1，所以默认只会有一个文件，而如果改变了reducer的数量，
 * 而并不改变Partitioner，则数据会被随机分配。
 */
public class ProvicePartitioner extends Partitioner<Text, FlowBean> {
    public static HashMap<String, Integer> proviceDice = new HashMap<>();
//    模拟数据库

    static {
        proviceDice.put("136", 0);
        proviceDice.put("137", 1);
        proviceDice.put("138", 2);
        proviceDice.put("139", 3);

    }

    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        String prefix = text.toString().substring(0, 3);
        Integer proviceID = proviceDice.get(prefix);
        return proviceID == null ? 4 : proviceID;
    }
}
