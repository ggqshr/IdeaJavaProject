package Spark.SortCustom

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 自定义排序的几种方式
  */
//隐式转换
/**
  * 隐式转换的方式，sortBy的科里化，第二个括号内隐式的接收一个Ordering
  */
object orderContext {
  //变量的形式
  implicit val Girl2Ordering = new Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      if (x.facaValue > y.facaValue) 1
      else if (x.facaValue == y.facaValue) {
        if (x.age > y.age) -1 else 1
      } else -1
    }
  }
  //object 的形式
  //  implicit object Girl2Ordering extends Ordering[Girl] {
  //    override def compare(x: Girl, y: Girl): Int = {
  //      if (x.facaValue > y.facaValue) 1
  //      else if (x.facaValue == y.facaValue) {
  //        if (x.age > y.age) -1 else 1
  //      } else -1
  //    }
  //  }

}

object CustomSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sorted").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("ggq", 90, 28, 1), ("dps", 89, 27, 1), ("swy", 100, 24, 1)))
    //第一种方式，自定义排序规则
    import orderContext._
    println(rdd1.sortBy(x => Girl(x._2, x._3), false).collect().toBuffer)
  }
}

///**
//  * 自定义排序类
//  * @param facaValue
//  * @param age
//  */
//case class Girl(facaValue: Int, age: Int) extends Ordered[Girl] with Serializable {
//  override def compare(that: Girl): Int = {
//    if (this.facaValue == that.facaValue) {
//      this.age - that.age
//    } else {
//      this.facaValue - that.facaValue
//    }
//  }
//}
case class Girl(facaValue: Int, age: Int) extends Serializable