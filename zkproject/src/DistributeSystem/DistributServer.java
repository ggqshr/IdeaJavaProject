package DistributeSystem;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by ggq on 2018/2/28.
 */
public class DistributServer {
    private ZooKeeper zk = null;
    private static final String connectString = "192.168.233.134:2181,192.168.233.135:2181,192.168.233.136:2181";
    private static final int sessiontimeour = 2000;
    private static final String parentNode = "/servers";

    public void getConnect() throws Exception {
        zk = new ZooKeeper(connectString, sessiontimeour, new Watcher() {
            @Override
                public void process(WatchedEvent event) {
                    //收到事件通知之后的回调函数
                    System.out.println(event.getType() + "---" + event.getPath());
                    try {
                        zk.getChildren("/", true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    public void registerServer(String hostName) throws KeeperException, InterruptedException {
        String create = zk.create(parentNode + "/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName + " is online " + create);
    }

    /**
     * 业务功能
     */
    public void handleBusiness(String hostName) throws InterruptedException {
        System.out.println(hostName + " is working");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String args[]) throws Exception {
        //获取zk连接
        DistributServer server = new DistributServer();
        server.getConnect();
        //利用zk注册服务器信息
        server.registerServer(args[0]);
        //启动业务线程
        server.handleBusiness(args[0]);
    }
}
