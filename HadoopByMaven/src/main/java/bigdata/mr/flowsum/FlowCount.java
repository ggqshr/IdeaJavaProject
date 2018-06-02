package bigdata.mr.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] filed = line.split("\t");
            String phone = filed[1];
            long upflow = Long.parseLong(filed[filed.length - 3]);
            long downflow = Long.parseLong(filed[filed.length - 2]);
            context.write(new Text(phone), new FlowBean(upflow, downflow));

        }

    }

    static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            Long allUpFlow = 0L;
            Long allDownFlow = 0L;
            for (FlowBean f : values) {
                allUpFlow += f.getUpFlow();
                allDownFlow += f.getDownFlow();
            }
            context.write(key, new FlowBean(allUpFlow, allDownFlow));
        }

    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "Mini1");
        Job job = Job.getInstance(conf);
        //写死的形式
//        job.setJar("jar的地址");
        job.setJarByClass(FlowCount.class);

        //指定map业务类
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //指定最终输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定输入文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //指定输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //将job的相关信息提交给yarn
//        job.submit();
        boolean s = job.waitForCompletion(true);
        System.exit(s ? 0 : 1);
    }
}
