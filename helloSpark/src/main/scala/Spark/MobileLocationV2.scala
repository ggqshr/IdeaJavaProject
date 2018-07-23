package Spark

import org.apache.spark.{SparkConf, SparkContext}

object MobileLocationV2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("userLocation").setMaster("local[2]")
    //.setMaster("spark://mini1:7077").set("spark.executor.memory","512m")
    val sc = new SparkContext(conf)
    val numberAndTime = sc.textFile("C:\\Users\\ggq18\\Desktop\\log_read\\input").map(x => {
      val line = x.split(",")
      val time = line(1)
      val timeLong = if (line(3) == "1") -time.toLong else time.toLong
      ((line(0), line(2)), timeLong)
    })
    val numberAndLastTime = numberAndTime.reduceByKey(_ + _).map(line => {
      val mobile = line._1._1
      val lac = line._1._2
      val time = line._2
      //基站,(电话，时间)
      (lac, (mobile, time))
    })
    val lac_info = sc.textFile("C:\\Users\\ggq18\\Desktop\\log_read\\lac_info.txt").map(line => {
      val f = line.split(",")
      //基站(经度，纬度)
      (f(0), (f(1), f(2)))
    })
    val lac_MobileAndTime_Info = numberAndLastTime.join(lac_info)
    val rdd4 = lac_MobileAndTime_Info.map(line => {
      val lac = line._1
      val mobile = line._2._1._1
      val time = line._2._1._2
      val x = line._2._2._1
      val y = line._2._2._2
      (mobile, lac, time, x, y)
    })
    println(rdd4.sortBy(_._3,false).groupBy(_._1).collect().toBuffer)
//    println(rdd4.collect().toBuffer)
  }
}
