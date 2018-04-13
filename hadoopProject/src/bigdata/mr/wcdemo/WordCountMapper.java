package bigdata.mr.wcdemo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * KEYIN :默认是mapreduce框架所读到的一行文本的起始偏移量 Long LongWrite是hadoop自带的序列化的Long
 * VALUESIN : 默认是MR框架所读到的一行文本的内容 String 同上 替代为 Text
 * KEYOUT : 是用户自定义逻辑完成之后输出数据中的KEY 在此处是单词 String
 * VALUEOUT : 是用户自定义逻辑处理完成之后输出数据中的value,在此处是单词次数 Integer 替换为IntWritable
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    /**
     * map阶段的业务逻辑就写在自定义的map方法中
     * maptask会对每一行输入数据调用一次我们自定义的map方法
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将maptask传给我们的文本内容先转换成String
        String line = value.toString();
        String[] words = line.split(" ");
        //将单词输出为<单词，1>
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
