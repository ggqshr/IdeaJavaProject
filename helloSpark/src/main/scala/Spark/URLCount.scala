package Spark

import java.net.URL

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

class HostPartitioner(ins: Array[String]) extends Partitioner {
  //规则map
  val parMap = new mutable.HashMap[String, Int]()
  var count = 0
  //传入的数据是学院的名称列表，这里将其映射到map中，相当于规则库
  for (i <- ins) {
    parMap += i -> count
    count += 1
  }

  override def numPartitions: Int = 3 //ins.length
  override def getPartition(key: Any): Int = {
    parMap.getOrElse(key.toString, 0)
  }
}

object URLCount {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setMaster("local[2]").setAppName("URLCOUNT")
    val sc = new SparkContext(config)
    val originData = sc.textFile("C:\\Users\\ggq18\\Desktop\\itcast.log")
    val UrlAndOne = originData.map(line => {
      val f = line.split("\t")
      //url ，1
      (f(1), 1)
    })
    val ReduceAndSorted = UrlAndOne.reduceByKey(_ + _).sortBy(_._2, false)
    val host_Url_Count = ReduceAndSorted.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
//      (host,(url,t._2)) //若要分区，需要map类型
    })
    //若要重新分区，使用partitionBy方法，传入自定的Partitioner
//    host_Url_Count.partitionBy(new HostPartitioner("传入学院的列表"))
    val grouped = host_Url_Count.groupBy(_._1)
    println(grouped.take(2).toBuffer)
    sc.stop()


  }
}
