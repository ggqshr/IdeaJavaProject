package bigdata.mr.LogTrans;

import bigdata.mr.wcdemo.WordCountDriver;
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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 清洗日志
 */
public class DataWish {
    static class DatawishMapper extends Mapper<LongWritable, Text, Text, LogBean> {
        SimpleDateFormat strampToDate = new SimpleDateFormat("dd/MMM/YYYY:hh:mm:ss", Locale.US);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        Text k = new Text();
        LogBean v = new LogBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split(" ");
            try {
                String referAddr = split[10];
                String requestAddr = split[6];
                String timeStramp = dateFormat.format(strampToDate.parse(split[3].substring(1)));
                String ipAdress = split[0];
                v.set(timeStramp, ipAdress, "", requestAddr, referAddr);
                k.set(ipAdress);
                context.write(k, v);
            } catch (Exception e) {


            }
        }
    }

    static class DataWishReducer extends Reducer<Text, LogBean, Text, NullWritable> {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        LogBean[] array = null;

        NullWritable v = NullWritable.get();
        Text k = new Text();

        @Override
        protected void reduce(Text key, Iterable<LogBean> values, Context context) throws IOException, InterruptedException {
            List<LogBean> tmpArray = new ArrayList<LogBean>();
            String session = "S";
            int sessionNum = 1;
            int thisIndex = 0;
            /**
             * 此处不能直接使用IteratorUtils.toList()方法，
             * 因为此方法的本质还是遍历iterator，然后将遍历的对象填入到list中，
             * 而每次遍历的对象，是同一个，添加到list中，也是相同的值，
             * 如果要将iterator转化成list或者array，必须手动遍历，如下所示，每次申请一个对象，
             * 这样才能够使值不相同。
             * 坑！！！！
             */
            for (LogBean value : values) {
                LogBean bean = new LogBean();
                try {
                    BeanUtils.copyProperties(bean,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                tmpArray.add(bean);
            }
            array = tmpArray.toArray(new LogBean[tmpArray.size()]);
            Arrays.sort(array);
            array[0].setSession(session + sessionNum);
            k.set(array[0].toString());
            context.write(k, v);
            for (int i = 1; i < this.array.length; i++) {
                try {
                    long from = simpleFormat.parse(array[thisIndex].getTime()).getTime();
                    long to = simpleFormat.parse(array[i].getTime()).getTime();
                    int minutes = (int) ((to - from) / (1000 * 60));
                    //如果间隔小于30分钟，就用同一个session
                    if (minutes <= 30) {
                        array[i].setSession(session + sessionNum);
                    } else {
                        thisIndex = i;
                        sessionNum++;
                        array[i].setSession(session + sessionNum);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                k.set(array[i].toString());
                context.write(k, v);
            }
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "local");
        conf.set("fs.defaultFs", "file:///");
        Job job = Job.getInstance(conf);
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(DatawishMapper.class);
        job.setReducerClass(DataWishReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LogBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean s = job.waitForCompletion(true);
        System.exit(s ? 0 : 1);
    }
}
