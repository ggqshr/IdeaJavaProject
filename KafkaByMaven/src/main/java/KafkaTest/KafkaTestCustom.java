package KafkaTest;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaTestCustom {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // 需要设置三个配置项，这三项没有默认值，
        //为key的序列化类
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //values 的序列化类
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // kafka server 的服务器地址
        properties.put("bootstrap.servers","Mini1:9092");
        //分组id
        properties.setProperty("group.id", "0");
        // 重要的是需要设置这里，设置重头开始读取订阅的生产者生产的数据。否则只会收到最新生产的数据。
        ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        properties.setProperty("auto.offset.reset", "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("shuaige");
        consumer.subscribe(strings);
        //这里的超时时间要设置长一点，要不然拿不到数据就认为是超时了
        ConsumerRecords<String,String> records = consumer.poll(1000);
        for (ConsumerRecord<String,String> recode: records) {
            System.out.println("recodeOffset = " + recode.offset() + "recodeValue = " + recode.value());
        }
        consumer.close();
    }
}


