package Spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StatfulWordCountStreaming {
  /**
    * Seq中装的是当前按照单词进行分组的数据
    * Option中装的是这个单词之前出现的次数，这个单词有可能是第一次出现，所以是option
    */
  val func: (Seq[Int], Option[Int]) => Option[Int] = {
    (x, y) => Option(x.sum + y.getOrElse(0))
  }
  /**
    * 另一种函数
    */
  val func: Iterator[(String, Seq[Int], Option[Int])] => Iterator[(String, Int)] = {
    /**
      * 三种书写方式
      */
    it =>
      it.flatMap(x => Some(x._2.sum + x._3.getOrElse(0)).map((x._1, _)))
      //    it => it.flatMap{case (x,y,z)=>Some(y.sum+z.getOrElse(0)).map((x,_))}
//      it.map(i => (i._1, i._2.sum + i._3.getOrElse(0)))
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("streamingDemo").setMaster("local[4]")
    System.setProperty("user.name", "hadoop")
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val sc = new SparkContext(conf)
    /**
      * 传入SparkContext和产生RDD的时间间隔
      */
    val ssc = new StreamingContext(sc, Seconds(5))
    //使用updateStateByKey的方式必须设置checkpoint
    ssc.checkpoint("D://ck")
    val ds = ssc.socketTextStream("192.168.101.128", 8888)
    val result = ds.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(func)
    result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
