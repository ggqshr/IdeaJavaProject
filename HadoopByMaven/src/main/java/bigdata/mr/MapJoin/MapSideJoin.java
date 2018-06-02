package bigdata.mr.MapJoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MapSideJoin {
    static class MapSideMapeer extends Mapper<LongWritable, Text, Text, NullWritable> {
        //保存产品信息表
        Map<String, String> pdInfoMap = new HashMap<String, String>();
        Text k = new Text();

        //在执行map函数前执行
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            BufferedReader bufferedInputStream;
            bufferedInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("product.txt")));
            String line;
            while (StringUtils.isNotEmpty(line = bufferedInputStream.readLine())) {
                String[] split = line.split(",");
                pdInfoMap.put(split[0], split[1]);
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");
            String pdName = pdInfoMap.get(split[1]);
            k.set(line + "\t" + pdName);
            context.write(k, NullWritable.get());
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf = new Configuration();
//        conf.set("mapreduce.framework.name", "yarn");
//        conf.set("yarn.resourcemanager.hostname", "Mini1");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//        conf.set("fs.defaultFs", "hdfs://Mini1:9000/");
////        conf.set("fs.defaultFs", "file:///");
        Job job = Job.getInstance(conf);
//        job.setJar("C:\\Users\\ggq\\Desktop\\test.jar");
        job.setJarByClass(MapSideJoin.class);
        //指定map端join的逻辑不需要reducer
        job.setNumReduceTasks(0);
        job.setMapperClass(MapSideMapeer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //指定需要缓存一个文件到所有的maptask运行节点工作目录
//        job.addArchiveToClassPath();  //缓存jar包到task运行节点的classpath中
//        job.addCacheArchive(); //缓存压缩包文件到task运行节点工作目录
//        job.addCacheFile();  //缓存普通文件到task运行节点工作目录
//        job.addFileToClassPath();//缓存到普通文件到task运行节点的claspath中

        //将产品信息表缓存到task工作节点的工作目录中去
        job.addCacheFile(new URI("file:///home/hadoop/product.txt"));
        boolean s = job.waitForCompletion(true);
        System.exit(s ? 0 : 1);
    }
}
