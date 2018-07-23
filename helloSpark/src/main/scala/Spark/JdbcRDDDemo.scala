package Spark


import java.sql.{Connection, DriverManager}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object JdbcRDDDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JdbcTest").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val getConn = ()=>{
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_text", "root", "123")
    }
    val jdbcRdd = new JdbcRDD(sc,
      getConn,
      "select * from jdallitem where price > ? and commit > ? ",
          0L,
      0L, 3,
      res => {
        val title = res.getString(1)
        val url = res.getString(2)
        (title,url)
      })
    println(jdbcRdd.collect().toBuffer)
    sc.stop()

  }
}
