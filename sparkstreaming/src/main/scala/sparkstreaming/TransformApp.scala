package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TransformApp {

  def main(args: Array[String]): Unit = {

    val sparkconf = new SparkConf()
      .setAppName("TransformApp")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")

    /**
      * sparkconf
      * duration
      */
    val ssc = new StreamingContext(sparkconf, Seconds(5))

    val blacks = List("zs","ls")

    val blacksRDD = ssc.sparkContext.parallelize(blacks).map(x=>(x,true))

    val line = ssc.socketTextStream("namenode", 6788)

    val lines = line.map(x=>(x.split(",")(1),x)).transform(rdd=>{
      rdd.leftOuterJoin(blacksRDD)
        .filter(x=>x._2._2.getOrElse(false)!=true)
        .map(x=>x._2._1)
    }
    )

    var result = lines.flatMap(_.split(",")).map((_, 1)).reduceByKey(_ + _).print()

    ssc.start()

    ssc.awaitTermination()
  }
}
