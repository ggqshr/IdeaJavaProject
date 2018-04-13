package bigdata.mr.ReduceJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;

public class RJoin {
    static class RJoinMapper extends Mapper<LongWritable, Text, Text, InfoBean> {
        InfoBean info = new InfoBean();
        Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            //获得处理切片的信息
            //在使用这个的时候，不要使用CombineTextInputFormat合并小文件
            //合并后就无法使用了，因为合并后，原来的文件名就存在了
            FileSplit split = (FileSplit) context.getInputSplit();
            String name = split.getPath().getName();
            String pid = "";
            if (name.startsWith("order")) {
                String[] fileds = line.split(",");
                pid = fileds[2];
                info.set(Integer.valueOf(fileds[0]), fileds[1], pid, Integer.valueOf(fileds[3]), "", 0, 0.0F, "0");
            } else {
                String[] fileds = line.split(",");
                pid = fileds[0];
                info.set(0, "", pid, 0, fileds[1], Integer.parseInt(fileds[2]), Float.parseFloat(fileds[3]), "1");
            }
            k.set(pid);
            context.write(k, info);
        }
    }

    static class RJoinReducer extends Reducer<Text, InfoBean, InfoBean, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<InfoBean> values, Context context) throws IOException, InterruptedException {
            InfoBean pdbean = new InfoBean();
            ArrayList<InfoBean> orderBeans = new ArrayList<>();

            for (InfoBean bean : values) {
                if ("1".equals(bean.getFlag())) {
                    try {
                        BeanUtils.copyProperties(pdbean, bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    InfoBean obean = new InfoBean();
                    try {
                        BeanUtils.copyProperties(obean, bean);
                        orderBeans.add(obean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (InfoBean bean : orderBeans) {
                bean.setPname(pdbean.getPname());
                bean.setCategory_id(pdbean.getCategory_id());
                bean.setPrice(pdbean.getPrice());
                context.write(bean, NullWritable.get());
            }
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "local");
        conf.set("fs.defaultFs", "file:///");
        Job job = Job.getInstance(conf);
        job.setJarByClass(RJoin.class);
        job.setMapperClass(RJoinMapper.class);
        job.setReducerClass(RJoinReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.submit();
    }
}
