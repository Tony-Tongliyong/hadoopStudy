package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import sparkstreaming.domain.ClickLog
import sparkstreaming.utils.DateUtils

object SparkKafkaApp {

  def main(args: Array[String]): Unit = {
    val sparkconf=new SparkConf()
      .setAppName("SparkStreamingTest")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")
    //sparkconf ,batch interval根据
    val ssc=new StreamingContext(sparkconf,Seconds(60))

    val stream = KafkaUtils.createStream(
      ssc,
      "192.168.86.128:2181",
      "test",
      Map("tly"-> 1)
    )
//    测试步骤一：测试数据接收
//    stream.map(_._2).count().print
//    测试步骤二：数据清洗
    val logs = stream.map(_._2)
    val cleanData = logs.map(line =>{
      val infos = line.split("\t")
      val url = infos(2).split(" ")(1)
      val courseId = 0

      if(url.startsWith("/class")){
        val courseIdHTML = url.split("/")(2)
        courseIdHTML.substring(0,courseIdHTML.lastIndexOf("."))
      }
      ClickLog(infos(0),DateUtils.parseToMinute(infos(1)),courseId,infos(3).toInt,infos(4))
    })
    ssc.start()
    ssc.awaitTermination()
  }

}
