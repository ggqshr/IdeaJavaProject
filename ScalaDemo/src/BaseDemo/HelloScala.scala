package BaseDemo

import scala.collection.mutable.ArrayBuffer

object HelloScala {
  def main(args: Array[String]): Unit = {
    //    var a = Array(1,2,3,4,5,6,7,8,9,10)
    //    var f1 : (Int,Int) => Int = {
    //      (x,y)=>x*y
    //    }
    //    val aa = a.map(f1(_,2 ))
    //    println(aa.toBuffer)
    val aaa = new ArrayBuffer[Int]()
    aaa += (1, 2, 3, 4, 5, 6)
    println(aaa.toBuffer)
    for (o <- aaa)println(o)

  }
}
