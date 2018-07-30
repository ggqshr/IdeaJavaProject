package Spark.KafkaDemo

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.immutable.HashMap

object OldKafkaWordCount {
  val func: (Seq[Int], Option[Int]) => Option[Int] = {
    (x, y) =>
      Some(y.getOrElse(0) + x.sum)
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KafkaDemo").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("d://ck")
    val topicMap = HashMap[String, Int]("shuaige" -> 2)
    //创建流的方式
    val dS = KafkaUtils.createStream(ssc, "mini1:2181,mini2:2181,mini3:2181", "ggq", topicMap, StorageLevel.MEMORY_AND_DISK)
    val result = dS.map(_._2).flatMap(_.split(" ")).map((_, 1)).updateStateByKey(func)
    result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
