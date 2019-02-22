package hbase;

import com.google.gson.annotations.Since;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class load2Hbase {
    public static class HdfsToHBaseMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Text outKey = new Text();
        private Text outValue = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");
            outKey.set(splits[0]);
            splits = Arrays.copyOfRange(splits, 1, splits.length);
            outValue.set(String.join("\t", splits));
            context.write(outKey, outValue);
        }
    }

    public static class HdfsToHBaseReducer extends TableReducer<Text, Text, NullWritable> {
        @Override
        protected void reduce(Text k2, Iterable<Text> v2s, Context context) throws IOException, InterruptedException {
            Put put = new Put(k2.getBytes());
            for (Text v2 : v2s) {
                String[] splis = v2.toString().split("\t");
                //info，对应hbase列族名
                if (splis[0] != null && !"NULL".equals(splis[0])) {
                    put.addColumn("personal data".getBytes(), "NodeCode".getBytes(), splis[0].getBytes());
                }
                if (splis[1] != null && !"NULL".equals(splis[1])) {
                    put.addColumn("personal data".getBytes(), "NodeType".getBytes(), splis[1].getBytes());
                }
                if (splis[2] != null && !"NULL".equals(splis[2])) {
                    put.addColumn("personal data".getBytes(), "NodeName".getBytes(), splis[2].getBytes());
                }
                if (splis[3] != null && !"NULL".equals(splis[3])) {
                    put.addColumn("personal data".getBytes(), "IsWarehouse".getBytes(), splis[3].getBytes());
                }
            }
            context.write(NullWritable.get(), put);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum", "101.132.186.3:2181,139.196.73.94:2181");//ip乱写的，端口默认2181
        conf.set("hbase.zookeeper.quorum", "master,slave");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("mapreduce.framework.name", "local");
        conf.set(TableOutputFormat.OUTPUT_TABLE, "emp");
        Job job = Job.getInstance(conf, load2Hbase.class.getSimpleName());
        TableMapReduceUtil.addDependencyJars(job);
        job.setJarByClass(load2Hbase.class);

        job.setMapperClass(HdfsToHBaseMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(HdfsToHBaseReducer.class);

        FileInputFormat.addInputPath(job, new Path("hdfs://139.196.73.94:9000/input/*"));
        job.setOutputFormatClass(TableOutputFormat.class);
        job.waitForCompletion(true);
    }
}
