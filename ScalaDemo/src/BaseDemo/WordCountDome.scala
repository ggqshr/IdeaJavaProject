package BaseDemo

object WordCountDome {
  def main(args: Array[String]): Unit = {
    val words = List("helle world hello tom", "hello jitty", "hello jack", "hello swy", "hello ggq")
    val f1 = (x:(String,List[(String,Int)]))=> ((x._1,x._2.size))
    val result = words.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(f1)
    val result1 = words.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
    val finalResult = result1.toList.sortBy(_._2).reverse
    println(finalResult.toBuffer)


  }
}
