package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: TestThread
 * @time: 2018/11/1 16:59
 * @desc:
 */
public class KafkaThread extends Thread{


    static Producer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.86.128:9093,192.168.86.128:9092,192.168.86.128:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(props);
    }
    @Override
    public void run() {
        int message = 1;
        while(true) {
//        synchronized (this) {
            producer.send(new ProducerRecord<String, String>("tly", Integer.toString(message), Integer.toString(message)));
//        }
            message++;
            System.out.println(message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}