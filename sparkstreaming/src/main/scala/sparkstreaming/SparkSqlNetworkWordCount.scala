package sparkstreaming

import java.io.File

import org.apache.spark.sql.SparkSession

object SparkSqlNetworkWordCount {

  def main(args: Array[String]): Unit = {
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath
    val spark = SparkSession
      .builder()
      .appName("Spark Statistic")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
  }
}
