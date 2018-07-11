package Storm.SimipStorm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountTopologMain {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        //1、准备一个TopologBuilder
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("mySpout", new MySpout(), 1);
        topologyBuilder.setBolt("mybolt1", new SplitBolt(), 10).shuffleGrouping("mySpout");
        topologyBuilder.setBolt("mybolt2", new countBolt(), 2).fieldsGrouping("mybolt1", new Fields("word"));
//        2、制定一个configuration，用来指定Topolog需要的wordker数量
        Config config = new Config();
        config.setNumWorkers(2);
//        3、提交任务应该有两种模式：本地和集群模式
        //集群模式
        StormSubmitter.submitTopology("mywordcount", config, topologyBuilder.createTopology());
        //本地模式
//        LocalCluster localCluster = new LocalCluster();
//        localCluster.submitTopology("mywordcount", config, topologyBuilder.createTopology());

    }
}
