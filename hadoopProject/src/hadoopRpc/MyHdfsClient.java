package hadoopRpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 客户端
 */
public class MyHdfsClient {
    public static void main(String args[]) throws IOException {
        ClientNamenodeProtocol proxy = RPC.getProxy(ClientNamenodeProtocol.class, 1L, new InetSocketAddress("localhost", 8888), new Configuration());
        String data = proxy.getMetaData("/test/test111");
        System.out.println(data);

    }
}
