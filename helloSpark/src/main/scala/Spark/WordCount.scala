package Spark

import org.apache.spark.{SparkContext, SparkConf}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wc")
      .set("spark.executor.memory","512m")
      .set("spark.executor.cores","1")
      .set("spark.submit.deployMode","cluster")
      .setMaster("spark://mini1:7077")
    val sc = new SparkContext(conf)
    sc.textFile("hdfs://Mini1:9000/wordcount/input")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, false)
      .saveAsTextFile("hdfs://Mini1:9000/wordcount/inputt")
    sc.stop()
  }
}
