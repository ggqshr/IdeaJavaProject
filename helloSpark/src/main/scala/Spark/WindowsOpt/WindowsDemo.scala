package Spark.WindowsOpt

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowsDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WindowDemo").setMaster("local")
    val ssc = new StreamingContext(conf, Seconds(5))
    val lines = ssc.socketTextStream("mini1", 9999)
    val pairs = lines.flatMap(_.split(" ")).map((_, 1))
    val windowWordCount = pairs.reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(15), Seconds(10))
    windowWordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
