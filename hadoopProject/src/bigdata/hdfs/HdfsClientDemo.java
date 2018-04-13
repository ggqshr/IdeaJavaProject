package bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

/**
 * 客户端操作hadoop时，是有一个用户身份的，
 * 默认情况下，hdfs客户端是是从jvm中获取一个参数当作自己的用户身份，
 * 我们可以通过设置启动选项设置用户名 -DHADOOP_USER_NAME=hadoop
 * 意思是设置用户名为hadoop，即为linux中的hadoop用户
 */
public class HdfsClientDemo {
    FileSystem fs;
    Configuration conf;

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {
        conf = new Configuration();//设置
        conf.set("fs.defaultFS", "hdfs://192.168.233.133:9000");
        //直接得到一个文件系统操作的客户实例对象
        /* fs = FileSystem.get(conf);*/
        //可以传入用户名以及文件系统地址的方式饿到文件系统的客户实例对象
        fs = FileSystem.get(new URI("hdfs://192.168.233.133:9000"), conf, "hadoop");
    }

    @Test
    public void testUpload() throws IOException {
        fs.copyFromLocalFile(new Path("d:/title.py"), new Path("/title11.py.log"));
        fs.close();
    }

    @Test
    public void testDownload() throws IOException {
        fs.copyToLocalFile(new Path("/title11.py.log"), new Path("d:/title.py.py"));
        fs.close();
    }

    /**
     * 显示所有conf中的参数
     */
    @Test
    public void testConf() {
        Iterator<Map.Entry<String, String>> iterator = conf.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> en = iterator.next();
            System.out.println(en.getKey() + ":" + en.getValue());
        }
    }

    @Test
    public void testMkdir() throws IOException {
        boolean b = fs.mkdirs(new Path("/testmkdir/aaa"));
        System.out.println(b);
    }

    @Test
    public void testdelete() throws IOException {
        fs.delete(new Path("/testmkdir"), true);
    }

    @Test
    public void testList() throws IOException {
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fs.listFiles(new Path("/"), true);
        while (locatedFileStatusRemoteIterator.hasNext()) {
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println(next.getBlockSize());
            System.out.println(next.getOwner());
            System.out.println(next.getPath());
        }
    }

    @Test
    public void testList2() throws IOException {
        FileStatus[] fileStatuses = this.fs.listStatus(new Path("/"));
        for (FileStatus f : fileStatuses
                ) {
            System.out.println(f.getPath());
        }

    }

    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration();//设置
        conf.set("fs.defaultFS", "hdfs://192.168.233.133:9000");
        //得到一个文件系统操作的客户实例对象
        FileSystem fs = FileSystem.get(conf);
        fs.copyFromLocalFile(new Path("d:/title.py"), new Path("/title.py.log"));
        fs.close();
    }

}
