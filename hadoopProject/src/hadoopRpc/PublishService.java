package hadoopRpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * 发布服务，运行rpc接口
 */
public class PublishService {
    public static void main(String args[]) throws IOException {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(ClientNamenodeProtocol.class)
                .setInstance(new MyNamenode());
        RPC.Server build = builder.build();
        build.start();
    }
}
