package kafka;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: KafkaTopicBean
 * @time: 2018/10/8 16:36
 * @desc:
 */
public class KafkaTopicBean {

    private String topicName;       // topic 名称
    private Integer partition;      // partition 分区数量
    private Integer replication;    // replication 副本数量
    private String describe;

    public KafkaTopicBean(String topicName, Integer partition, Integer replication, String describe) {
        this.topicName = topicName;
        this.partition = partition;
        this.replication = replication;
        this.describe = describe;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public Integer getReplication() {
        return replication;
    }

    public void setReplication(Integer replication) {
        this.replication = replication;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}