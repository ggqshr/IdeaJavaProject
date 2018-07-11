package KafkaStorm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.TopologyBuilder;

public class KafkaStormTest {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("KafkaSpout",
                new KafkaSpout(
                        new SpoutConfig(new ZkHosts("Mini1:2181,Mini2:2181,Mini3:2181"),
                                "kafkatest","/mykafka","kafkastorm")),
                1);
        topologyBuilder.setBolt("mybolt1",new myKafkaBolt1(),1).shuffleGrouping("KafkaSpout");
        Config config = new Config();
        config.setNumWorkers(2);
//        StormSubmitter.submitTopology("myKafka", config, topologyBuilder.createTopology());
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("mykafkatest",config,topologyBuilder.createTopology());
    }
}
