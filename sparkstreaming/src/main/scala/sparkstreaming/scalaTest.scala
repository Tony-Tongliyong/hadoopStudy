package sparkstreaming

object scalaTest {

  def sum(xs: Int*) = (0 /: xs)((x, y) => x + y)

  def main(args: Array[String]): Unit ={
    println(sum(List(1,2,3,4): _*))
  }

}
