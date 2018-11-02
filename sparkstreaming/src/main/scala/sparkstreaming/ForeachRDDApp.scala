package sparkstreaming

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ForeachRDDApp {

  def main(args: Array[String]): Unit = {

    val sparkconf = new SparkConf()
      .setAppName("ForeachRDDApp")
      .setMaster("local[2]")
      .set("spark.executor.memory", "1g")

    /**
      * sparkconf
      * duration
      */
    val ssc = new StreamingContext(sparkconf, Seconds(5))

//    ssc.checkpoint("hdfs://namenode:9000/data")

    val lines = ssc.socketTextStream("namenode", 6788)

    var result = lines.flatMap(_.split("")).map((_, 1)).reduceByKey(_+_)

    result.print()
    result.foreachRDD{ rdd =>
      print(rdd)
      rdd.foreachPartition(partitionRecords=>{
        print(partitionRecords)
          val connection = createConnection() // executed at the driver
          partitionRecords.foreach { record =>
            print(record)
            var sql = "insert into wordcount (word,wordcount) values ('" + record._1 + "'," + record._2 + ")"
            var statement = connection.createStatement()
            statement.execute(sql)
          }
          connection.close()

    })
    }
//    state.print

    ssc.start()
    ssc.awaitTermination()
  }

  def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
    val current= currentValues.sum // add the new values with the previous running count to get the new count
    val pre = preValues.getOrElse(0)
    Some(current + pre)
  }

  /**
    * 获取mysql的连接
    * @return
    */
  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/daji","root","root")
  }
}
