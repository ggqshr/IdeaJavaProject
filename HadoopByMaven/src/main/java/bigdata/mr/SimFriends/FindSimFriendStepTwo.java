package bigdata.mr.SimFriends;

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
import java.util.Arrays;

/**
 * 寻找具有相同好友的用户的第二部
 * 将第一步的结果读入，然后对具有相同好友的用户列表进行排序，然后遍历输出
 */
public class FindSimFriendStepTwo {
    static class FindSimFriendStepTwoMapper extends Mapper<LongWritable, Text, Text, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");
            String[] persons = split[1].split("--");
            String friend = split[0];
            Arrays.sort(persons);//按照字典序排序
            for (int i = 0; i < persons.length - 2; i++) {
                for (int j = i + 1; j < persons.length - 1; j++) {
                    context.write(new Text(persons[i] + "--" + persons[j]), new Text(friend));
                }
            }
        }
    }

    static class FindSimFriendStepTwoReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuffer sb = new StringBuffer();
            for (Text t : values) {
                sb.append(t + ",");
            }
            context.write(key, new Text(sb.toString()));
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        Job job = Job.getInstance(conf);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(FindSimFriendStepTwoMapper.class);
        job.setReducerClass(FindSimFriendStepTwoReducer.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean re = job.waitForCompletion(true);
        System.exit(re ? 1 : 0);
    }
}
