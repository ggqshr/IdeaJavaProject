package bigdata.mr.xiecheng;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionByEmotiom extends Partitioner<SimipBean, IntWritable> {
    @Override
    public int getPartition(SimipBean simipBean, IntWritable intWritable, int i) {
        if (simipBean.getEmotiom().equals("正面")) {
            return 0;
        } else {
            return 1;
        }
    }
}
