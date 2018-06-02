package bigdata.mr.wcdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    /**
     * 相当于一个yarn集群的客户端
     * 需要我们封装我们mr程序的相关运行参数，指定jar包，最后提交给yarn
     *
     * @param args
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        /*若要使用java -jar 的方式运行程序，需要设置这个参数，否则会报
        No FileSystem for scheme "hdfs"的错误*/
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        //用于在虚拟机中跑跑程序，需要设置
        /*conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "Mini1");*/
        //在本地跑mr时，需要这样设置
//        conf.set("mapreduce.framework.name", "local");
//        本地运行mr时，输入输出的数据可以在本地，也可以在hdfs上
        /*在本地
        conf.set("fs.defaultFs", "file:///");*/
//        在hdfs上
        conf.set("fs.defaultFs", "hdfs://Mini1:9000/");

        Job job = Job.getInstance(conf);
        /**
         * 在虚拟机上用java -jar的方式运行时，需要手动写上jar包的地址
         * 否则找不到
         */
        job.setJar("C:\\Users\\ggq\\Desktop\\test.jar");
//        job.setJarByClass(WordCountDriver.class);
        //设置Combiner类
        job.setCombinerClass(WordCountCombiner.class);
        /*设置inputFormat类
        如果不设置，那么默认是Textinputformat
        如果小文件过多，可以使用如下的CombineTextInputFormat
        他会将小文件在逻辑上合并*/
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
        CombineTextInputFormat.setMinInputSplitSize(job, 2097152);
        //指定map业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定最终输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
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
