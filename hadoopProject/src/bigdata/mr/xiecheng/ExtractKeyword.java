package bigdata.mr.xiecheng;

import bigdata.mr.wcdemo.WordCountDriver;
import bigdata.mr.wcdemo.WordCountMapper;
import bigdata.mr.wcdemo.WordCountReducer;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * 提取关键字中出现次数最多的mapreduce
 */
public class ExtractKeyword {
    static class ExtractKeywordMapper extends Mapper<LongWritable, Text, SimipBean, IntWritable> {
        SimipBean sb = new SimipBean();
        PerceptronLexicalAnalyzer analyzer;

        {
            try {
                analyzer = new PerceptronLexicalAnalyzer();
                analyzer.enablePlaceRecognize(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");

            String keywords = null;
            try {
                keywords = split[13];
                for (String keyword : keywords.split(",")) {
                    String emotion = split[11];
                    /**
                     * 过滤一下分类错误的评论，若评分很高，但是为负面，则变为正面
                     */
                    if (Integer.valueOf(split[8]) == 5) {
                        emotion = "正面";
                    }
                    /**
                     * 去掉青岛和景区这种词汇，对打标签无用处
                     */
                    if (keyword.equals("青岛") || keyword.equals(split[10]) || keyword.equals("景区")) {
                        continue;
                    }
                    Sentence tag = analyzer.analyze(keyword);
                    /**
                     * 如果分词有误，则学习他
                     */
                    if (tag.wordList.size() != 1) {
                        analyzer.learn(keyword + "/n");
                        tag = analyzer.analyze(keyword);
                    }
                    /**
                     * 过滤掉副词，时间，人名，动词，方位词
                     */
                    if (tag.containsWordWithLabel("d") || tag.containsWordWithLabel("t") || tag.containsWordWithLabel("nr") || tag.containsWordWithLabel("v") || tag.containsWordWithLabel("f")) {
                        continue;
                    }
                    sb.set(split[10], emotion, keyword);
                    context.write(sb, new IntWritable(1));
                }
            } catch (Exception e) {

            }
        }
    }

    static class ExtractKeywordReducer extends Reducer<SimipBean, IntWritable, SimipBean, IntWritable> {
        @Override
        protected void reduce(SimipBean key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count += value.get();
            }
            context.write(key, new IntWritable(count));
        }
    }

    /**
     * 使用hadoop jar运行
     * @param a1
     * @param a2
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static boolean runUnderHadoop(String a1,String a2) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        Job job = Job.getInstance(conf);
        job.setJar("/home/hadoop/test.jar");
        job.setJarByClass(WordCountDriver.class);
        job.setNumReduceTasks(2);
        job.setMapperClass(ExtractKeywordMapper.class);
        job.setReducerClass(ExtractKeywordReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(SimipBean.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定最终输出的kv类型
        job.setOutputKeyClass(SimipBean.class);
        job.setOutputValueClass(IntWritable.class);
        //指定Partitioner
        job.setPartitionerClass(PartitionByEmotiom.class);
        //指定GroupComparator
        job.setGroupingComparatorClass(GroupComparatorByPlaceAndKeyword.class);
        //指定输入文件所在目录
        FileInputFormat.setInputPaths(job, new Path(a1));
        //指定输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(a2));
        //将job的相关信息提交给yarn
        return  job.waitForCompletion(true);
    }

    /**
     * 本地模式运行
     * @param a1
     * @param a2
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static boolean runUnderLocal(String a1,String a2) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name","local");
        conf.set("fs.defaultFs","file:///");
        Job job = Job.getInstance(conf);
        job.setJarByClass(WordCountDriver.class);
        job.setNumReduceTasks(2);
        job.setMapperClass(ExtractKeywordMapper.class);
        job.setReducerClass(ExtractKeywordReducer.class);
        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(SimipBean.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定最终输出的kv类型
        job.setOutputKeyClass(SimipBean.class);
        job.setOutputValueClass(IntWritable.class);
        //指定Partitioner
        job.setPartitionerClass(PartitionByEmotiom.class);
        //指定GroupComparator
        job.setGroupingComparatorClass(GroupComparatorByPlaceAndKeyword.class);
        //指定输入文件所在目录
        FileInputFormat.setInputPaths(job, new Path(a1));
        //指定输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(a2));
        //将job的相关信息提交给yarn
        return  job.waitForCompletion(true);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        boolean s = runUnderLocal(args[0],args[1]);
        System.exit(s ? 0 : 1);
    }
}
