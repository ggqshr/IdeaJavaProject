package bigdata.mr.LogEnhance;

import Utils.JdbcUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.HashMap;

/**
 * 日志增强
 */
public class LogEnhance {
    static class LogEnhanceMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        HashMap<String, String> ruleMap = new HashMap<String, String>();
        Text k = new Text();
        NullWritable v = NullWritable.get();

        //从数据中加载数据到map中
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            try {
                JdbcUtil.dbLoader(ruleMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //mapreducer提供的全局的计数器,用来记录不合法的行数
            Counter counter = context.getCounter("malformd", "malformedline");
            String line = value.toString();
            //这里使用StringUtils得到的结果与String.split得到的结果由不同
            //StringUtils得到的结果不含有空行
            String[] split = StringUtils.split(line, "\t");
            try {
                String url = split[26];
                String content = ruleMap.get(url);
                //判断内容标签是否为空
                if (content == null) {
                    k.set(url + "\t" + "tocrawl" + "\n");
                    context.write(k, v);
                } else {
                    k.set(line + "\t" + content + "\n");
                    context.write(k, v);
                }
            } catch (Exception e) {
                counter.increment(1);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //设置jvm参数
        System.setProperty("HADOOP_USER_NAME","hadoop");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        Job job = Job.getInstance(conf);
        job.setJar("C:\\Users\\ggq\\Desktop\\test.jar");
        job.setNumReduceTasks(0);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setMapperClass(LogEnhanceMapper.class);
        job.setOutputFormatClass(LogEnhanceOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //尽管自定义了outputformat，也还是要设置一个输出目录，用来输出success文件
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean s = job.waitForCompletion(true);
        System.exit(s ? 0 : 1);
    }
}
