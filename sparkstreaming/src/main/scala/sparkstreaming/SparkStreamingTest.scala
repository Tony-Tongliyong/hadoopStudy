package sparkstreaming


import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
object SparkStreamingTest {

  def main(args: Array[String]): Unit ={

    val sparkconf=new SparkConf()
      .setAppName("tlyTest")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")
    //sparkconf ,batch interval根据
    val ssc=new StreamingContext(sparkconf,Seconds(5))
    //kafka参数
    val kafkaParams = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG ->"192.168.86.128:9093",
      ConsumerConfig.GROUP_ID_CONFIG -> "test",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])
    //SparkStreaming需要通过checkpoint来容错，以便于在任务失败的时候可以从checkpoint里面恢复
    ssc.checkpoint("hdfs://namenode:9000/data")
    val topics = Array("tly")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams))
    val lines=stream.map(_.value)
    val word=lines.flatMap(_.split(",")).map(x=>(x,1)).reduceByKey(_+_).print
    ssc.start()
    ssc.awaitTermination()
  }
}
