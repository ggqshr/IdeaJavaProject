package bigdata.mr.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCountSort {
    static class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

        FlowBean bean = new FlowBean();
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //那到的是上一个统计程序的输出结果，
            String line = value.toString();
            String[] split = line.split("\t");
            String phone = split[0];
            long upflow = Long.parseLong(split[1]);
            long downflow = Long.parseLong(split[2]);
            bean.setAll(upflow, downflow);
            v.set(phone);
            context.write(bean, v);
        }
    }

    static class FlowCountSortReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
        @Override
        protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(values.iterator().next(), key);
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "Mini1");
        Job job = Job.getInstance(conf);
        //写死的形式
//        job.setJar("jar的地址");
        job.setJarByClass(FlowCountSort.class);

        //指定map业务类
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        //指定最终输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定输入文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //指定输出结果所在目录
        Path path = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(path)) {
            fs.delete(path, true);
        }
        FileOutputFormat.setOutputPath(job, path);
        //将job的相关信息提交给yarn
//        job.submit();
        boolean s = job.waitForCompletion(true);
        System.exit(s ? 0 : 1);
    }
}
