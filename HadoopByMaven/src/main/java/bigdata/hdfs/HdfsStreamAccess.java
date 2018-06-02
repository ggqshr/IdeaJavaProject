package bigdata.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 用流的方式来操作hdfs上的文件，
 * 可以实现读取指定偏移量范围的数据
 */
public class HdfsStreamAccess {
    private FileSystem fs;
    private Configuration conf;

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {
        conf = new Configuration();//设置
        conf.set("fs.defaultFS", "hdfs://192.168.233.133:9000");
        fs = FileSystem.get(new URI("hdfs://192.168.233.133:9000"), conf, "hadoop");
    }

    /**
     * 通过流的方式上传文件
     *
     * @throws IOException
     */
    @Test
    public void testUpload() throws IOException {
        FSDataOutputStream out = fs.create(new Path("/test.txt"), true);
        FileInputStream filein = new FileInputStream("d:/title.py");
        IOUtils.copy(filein, out);

    }

    /**
     * 用流的方式获取hdfs上的文件
     *
     * @throws IOException
     */
    @Test
    public void testDownload() throws IOException {
        FSDataInputStream in = fs.open(new Path("/test.txt"));
        FileOutputStream fileput = new FileOutputStream("d:/ggq.txt");
        IOUtils.copy(in, fileput);
    }

    /**
     * 从指定位置开始读取
     *
     * @throws IOException
     */
    @Test
    public void testRandomAccess() throws IOException {
        FSDataInputStream open = fs.open(new Path("/test.txt"));
        //设定读取的起始位置
        open.seek(20);
        FileOutputStream fileput = new FileOutputStream("d:/ggq.txt");
        IOUtils.copy(open, fileput);
    }

    /**
     * 将文件中的内容输出到屏幕上
     * @throws IOException
     */
    @Test
    public void testShowData() throws IOException {
        FSDataInputStream open = fs.open(new Path("/test.txt"));
        //将内容输出到标准输出
        IOUtils.copy(open, System.out);
    }
}
