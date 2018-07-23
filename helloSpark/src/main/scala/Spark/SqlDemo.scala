package Spark

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SqlDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SqlDemo").setMaster("local[4]")
    //必须写在注册SparkContext之前
    System.setProperty("user.name", "hadoop")
    System.setProperty("HADOOP_USER_NAME","hadoop")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val data = sc.textFile("hdfs://Mini1:9000/person.txt").map(x => {
      val f = x.split(",")
      Person(f(0).toLong, f(1), f(2).toInt)
    })
    //这里需要导入一个隐式转换,才有toDF方法
    import sqlContext.implicits._
    val personDF = data.toDF
    personDF.createOrReplaceTempView("person")
    sqlContext.sql("select * from person where age >20").show()
    personDF.write.json("hdfs://mini1:9000/person.json")
    sc.stop()
  }
}

case class Person(id: Long, name: String, age: Int)
