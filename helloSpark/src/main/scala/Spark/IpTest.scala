package Spark

import org.apache.spark.{SparkConf, SparkContext}

object IpTest {
  def Ip2Ling(ip: String): Long = {
    val ipArr = ip.split("\\.").map(_.toLong).reverse
    var count = 0L
    var sum = 0L
    for (i <- ipArr) {
      sum |= i << count
      count += 8
    }
    sum
  }

  def bandraySearch(line: Array[(String, String, String)], ip: Long): Int = {
    var low = 0
    var high = line.length - 1
    while (low <= high) {
      var mid = (low + high) / 2
      if ((ip >= line(mid)._1.toLong) && (ip <= line(mid)._2.toLong))  return mid
      if (ip < line(mid)._1.toLong) {high = mid - 1}
      if (ip > line(mid)._2.toLong) {low = mid + 1}
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("urllocation").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val ipRule = sc.textFile("C:\\Users\\ggq18\\Desktop\\ip.txt").map(line => {
      val f = line.split("\\|")
      val start_num = f(2)
      val end_num = f(3)
      val province = f(6)
      (start_num, end_num, province)
    })
    // 将上述结果产生的rdd收集到driver中，然后进行广播
    val ipRultArr = ipRule.collect()
    //广播数据,广播完成后，在下面使用规则时，需使用广播函数返回的变量，
    val ipRulecase = sc.broadcast(ipRultArr)
    var data = sc.textFile("C:\\Users\\ggq18\\Desktop\\20090121000132.394251.http.format").map(line => {
      val f = line.split("\\|")
      f(1)
    })
    var data2Long = data.map(line => {
      val longData = Ip2Ling(line)
      val index = bandraySearch(ipRulecase.value, longData)
      val info = ipRulecase.value(index)
      info
    })
    println(data2Long.collect().toBuffer)
  }
}
