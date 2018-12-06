package sparkstreaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreamingTest2 {

  def main(args: Array[String]): Unit ={

    val sparkconf=new SparkConf()
      .setAppName("SparkStreamingTest2")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")
    //sparkconf ,batch interval根据
    val ssc=new StreamingContext(sparkconf,Seconds(5))
    //kafka参数
    val kafkaParams = Map[String, String](
     "metadata.broker.list" -> "192.168.86.128:9093")
//    SparkStreaming需要通过checkpoint来容错，以便于在任务失败的时候可以从checkpoint里面恢复
//    ssc.checkpoint("hdfs://namenode:9000/data")

    val Dstream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
      ssc,
      kafkaParams,
      Set("tly")
    )
    val lines=Dstream.map(_._2)
    val word=lines.flatMap(_.split(",")).map(x=>(x,1)).reduceByKey(_+_).print
    ssc.start()
    ssc.awaitTermination()
  }
}
