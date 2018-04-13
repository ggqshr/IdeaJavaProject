package bigdata.mr.LogEnhance;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogEnhanceOutputFormat extends FileOutputFormat<Text, NullWritable> {
    /**
     * 最终输出时，先调用outputformat的getRecordWriter方法拿到一个RecordWriter
     * 然后再调用RecordWriter的write，在调用
     *
     * @param taskAttemptContext
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(taskAttemptContext.getConfiguration());
        Path enhencePath = new Path("hdfs://Mini1:9000/logenhance/enhencelog/log.data");
        Path toCrawlPath = new Path("hdfs://Mini1:9000/logenhance/toCrawl/log.data");
        FSDataOutputStream enhanceinput = fs.create(enhencePath);
        FSDataOutputStream tocrawlinput = fs.create(toCrawlPath);
        return new EnhanceRecordWriter(enhanceinput, tocrawlinput);
    }

    /**
     * 构造一个自己的RecordWriter
     */
    static class EnhanceRecordWriter extends RecordWriter<Text, NullWritable> {
        FSDataOutputStream enhanceinput = null;
        FSDataOutputStream tocrawlinput = null;

        public EnhanceRecordWriter(FSDataOutputStream enhanceinput, FSDataOutputStream tocrawlinput) {
            this.enhanceinput = enhanceinput;
            this.tocrawlinput = tocrawlinput;
        }

        @Override
        public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
            String result = text.toString();
            //如果写入的数据是待爬取的url，就将其写入到清单文件中去
            if (result.contains("tocrawl")) {
                tocrawlinput.write(result.getBytes());
            } else {
                enhanceinput.write(result.getBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            if (enhanceinput != null)
                enhanceinput.close();
            if (tocrawlinput != null) {
                tocrawlinput.close();
            }
        }
    }
}
