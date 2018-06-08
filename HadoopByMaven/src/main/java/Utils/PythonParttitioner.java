package Utils;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class PythonParttitioner implements Partitioner<Text, Text> {
    @Override
    public int getPartition(Text text, Text text2, int i) {
        if (text.toString().startsWith("n")) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void configure(JobConf jobConf) {

    }
}
