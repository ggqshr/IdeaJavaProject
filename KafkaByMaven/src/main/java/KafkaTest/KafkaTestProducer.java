package KafkaTest;


import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KafkaTestProducer {
    public static void main(String[] args) {
        String TOPIC = "shuaige";
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers","Mini1:9092");
        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<String, String>(properties);
        stringStringKafkaProducer.send(new ProducerRecord<String, String>(TOPIC, "this message from idea"));
        //记得要关闭，否则不会写入到集群中。
        stringStringKafkaProducer.close();
    }
}
