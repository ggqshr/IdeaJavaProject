package Spark

import org.apache.spark.{SparkConf, SparkContext}

object MobileLocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("userLocation").setMaster("local[2]")//.setMaster("spark://mini1:7077").set("spark.executor.memory","512m")
    val sc = new SparkContext(conf)
    val numberAndTime = sc.textFile("C:\\Users\\ggq18\\Desktop\\log_read").map(x => {
      val line = x.split(",")
      val time = line(1)
      val timeLong = if (line(3) == "1") -time.toLong else time.toLong
      (line(0) + "_" + line(2), timeLong)
    })
    println(numberAndTime.reduceByKey(_+_).sortBy(_._2,false).groupBy(_._1.split("_")(0)).collect().toBuffer)
    sc.stop()
  }
}
