package sparkstreaming.utils

import java.util.Date

import org.apache.commons.lang3.time.FastDateFormat


object DateUtils {

  val YYYYMMDDHHMMSS_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
  val TAGRE_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss")

  def getTime(time:String) ={
    YYYYMMDDHHMMSS_FORMAT.parse(time).getTime
  }

  def parseToMinute(time:String)={
    TAGRE_FORMAT.format(new Date(getTime(time)))
  }

  def main(args: Array[String]): Unit = {
    println(parseToMinute("2018-09-11 13:23:23"))
  }
}
