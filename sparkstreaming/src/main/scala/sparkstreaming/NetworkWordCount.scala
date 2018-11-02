package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object NetworkWordCount {

  def main(args: Array[String]): Unit = {

    val sparkconf = new SparkConf()
      .setAppName("NetworkWordCount")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")

    /**
      * sparkconf
      * duration
      */
    val ssc = new StreamingContext(sparkconf, Seconds(5))

    val lines = ssc.socketTextStream("namenode", 6789)

    var word = lines.flatMap(_.split("")).map((_, 1)).reduceByKey(_ + _).print()

    ssc.start()

    ssc.awaitTermination()
  }

}
