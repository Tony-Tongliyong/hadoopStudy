package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StatefulWordCount {

  def main(args: Array[String]): Unit = {

    val sparkconf = new SparkConf()
      .setAppName("StatefulWordCount")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")

    /**
      * sparkconf
      * duration
      */
    val ssc = new StreamingContext(sparkconf, Seconds(5))

    ssc.checkpoint("hdfs://namenode:9000/data")

    val lines = ssc.socketTextStream("namenode", 6788)

    var result = lines.flatMap(_.split("")).map((_, 1))

    var state = result.updateStateByKey[Int](updateFunction _)

    state.print

    ssc.start()

    ssc.awaitTermination()
  }

  def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
    val current= currentValues.sum // add the new values with the previous running count to get the new count
    val pre = preValues.getOrElse(0)
    Some(current + pre)
  }

}
