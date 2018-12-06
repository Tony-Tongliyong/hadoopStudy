package sparkstreaming


import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
object SparkStreamingTest {

  def main(args: Array[String]): Unit ={

    val sparkconf=new SparkConf()
      .setAppName("SparkStreamingTest")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")
    //sparkconf ,batch interval根据
    val ssc=new StreamingContext(sparkconf,Seconds(5))

    val stream = KafkaUtils.createStream(
      ssc,
      "192.168.86.128:2181",
      "test",
      Map("tly"-> 1)
    )

    val lines=stream.map(_._2)
    val word = lines.flatMap(_.split("")).map(x=>(x,1)).reduceByKey(_+_).print
    ssc.start()
    ssc.awaitTermination()
  }
}
