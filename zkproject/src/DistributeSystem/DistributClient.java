package DistributeSystem;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggq on 2018/3/8.
 */
public class DistributClient {

    private ZooKeeper zk = null;
    private static final String connectString = "192.168.233.134:2181,192.168.233.135:2181,192.168.233.136:2181";
    private static final int sessiontimeour = 2000;
    private static final String parentNode = "/servers";
    private volatile List<String> serverList;

    public void getConnect() throws Exception {
        zk = new ZooKeeper(connectString, sessiontimeour, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到事件通知之后的回调函数
                try {
                    //重新更新服务器列表，
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取服务器信息列表
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getServerList() throws KeeperException, InterruptedException {
        //获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zk.getChildren(parentNode, true);
        List<String> servers = new ArrayList<String>();
        for (String ss : children) {
            byte[] name = zk.getData(parentNode + "/" + ss, false, null);
            servers.add(new String(name));
        }
        serverList = servers;
        System.out.println(serverList);
    }

    /**
     * 业户函数
     * @throws InterruptedException
     */
    public void handleBussiness() throws InterruptedException {
        System.out.println("client is working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String args[]) throws Exception {
        //获取zookeeper连接
        DistributClient dc = new DistributClient();
        dc.getConnect();
        //获取servers的子节点信息，从中获取服务器信息列表
        dc.getServerList();
        //业务线程
        dc.handleBussiness();
    }
}
