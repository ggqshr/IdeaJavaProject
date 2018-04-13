import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Formattable;
import java.util.List;

/**
 * Created by ggq on 2018/2/28.
 */
public class zkClient {
    private static final String connectString = "192.168.233.134:2181,192.168.233.135:2181,192.168.233.136:2181";
    private static final int sessiontimeour = 2000;
    ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessiontimeour, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到事件通知之后的回调函数
                System.out.println(event.getType() + "---" + event.getPath());
                try {
                    zkClient.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建子节点
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testcreate() throws KeeperException, InterruptedException {
        String nodeCreated = zkClient.create("/intellij", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取子节点
     */
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> childrens = zkClient.getChildren("/", true);
        for (String s : childrens) {
            System.out.println(s);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 判断目录是否存在
     */
    @Test
    public void testexist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/intellij", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }

    /**
     * 获取Znode的数据
     */
    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/intellij", false, new Stat());
        System.out.println(new String(data));
    }

    /**
     * 删除Znode的数据
     */
    @Test
    public void deleteZnode() throws KeeperException, InterruptedException {
        //-1是删除所有版本
        zkClient.delete("/intellij", -1);
    }

    /**
     * 修改数据
     */
    @Test
    public void setData() throws KeeperException, InterruptedException {
        zkClient.setData("/intellij", "zk hello".getBytes(), -1);
    }
}
