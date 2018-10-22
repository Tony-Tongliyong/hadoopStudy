package kafka;


import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;


/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: KafkaTest
 * @time: 2018/10/8 16:06
 * @desc:
 */
public class KafkaTest {
    /**
     * 创建topic
     */
    @Test
    public  void createTopics() {
        KafkaTopicBean topic = new KafkaTopicBean("topic",1,1,"");
        ZkUtils zkUtils = ZkUtils.
                apply("192.168.86.128:2181", 30000, 30000,JaasUtils.isZkSecurityEnabled());
        AdminUtils.createTopic(zkUtils, topic.getTopicName(),  topic.getPartition(),
                topic.getReplication(),  new Properties(), RackAwareMode.Enforced$.MODULE$);
        zkUtils.close();
    }

    /**
     * 生产者
     */
    @Test
    public  void produceTest(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.86.128:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        Integer i = 0;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            producer.send(new ProducerRecord<String, String>("topic",i.toString() , str));
            i++;
        }
    }

    /**
     * 消费者，将kafka数据插入hbase
     * @throws IOException
     */
    @Test
    public  void consumerTest() throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.86.128:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("topic"),new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
//                //将偏移设置到最开始
//                consumer.seekToBeginning(collection);
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                put(record.value());
            }
        }
    }

    /**
     * hbase插入数据
     * @param string
     * @throws IOException
     */
    public  void put(String string) throws IOException {
        //设置HBase据库的连接配置参数
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",  "192.168.86.128:2181");
        Random random = new Random();
        long a = random.nextInt(1000000000);
        String tableName = "emp";
        String rowkey = "rowkey"+a ;
        String columnFamily = "basicinfo";
        String column = "empname";
        //String value = string;
        HTable table=new HTable(conf, tableName);
        Put put=new Put(Bytes.toBytes(rowkey));
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(string));
        table.put(put);
        System.out.println("放入成功");
        table.close();
    }
}