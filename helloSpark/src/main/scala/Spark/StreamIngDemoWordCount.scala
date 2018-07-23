package Spark

import org.apache.spark.streaming._
import org.apache.spark.{SparkConf, SparkContext}

object StreamIngDemoWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("streamingDemo").setMaster("local[4]")
    System.setProperty("user.name", "hadoop")
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val sc = new SparkContext(conf)
    /**
      * 传入SparkContext和产生RDD的时间间隔
      */
    val ssc = new StreamingContext(conf, Seconds(5))
    val ds = ssc.socketTextStream("192.168.101.128", 8888)
    val result = ds.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
