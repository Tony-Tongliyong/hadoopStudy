package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FileWordCount {

  def main(args: Array[String]): Unit = {

    val sparkconf = new SparkConf()
      .setAppName("FileWordCount")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")

    /**
      * sparkconf
      * duration
      */
    val ssc = new StreamingContext(sparkconf, Seconds(5))

    val lines = ssc.textFileStream("hdfs://namenode:9000/tly/")

    var word = lines.flatMap(_.split("")).map((_, 1)).reduceByKey(_ + _).print()

    ssc.start()

    ssc.awaitTermination()

  }
}
