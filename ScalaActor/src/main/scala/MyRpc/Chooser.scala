package MyRpc

object Context {

  /**
    * 对应视图界定和上下文界定的方式
    */
  //  implicit def girl2Ordered(g: Girl) = new Ordered[Girl] {
  //    override def compare(that: Girl): Int = {
  //      g.faceValue - that.faceValue
  //    }
  //  }
  /**
    * 视图界定和上下文界定另一种书写方式
    */
  //    implicit val girl2Ordered = (g: Girl) => new Ordered[Girl] {
  //    override def compare(that: Girl): Int = {
  //      g.faceValue - that.faceValue
  //    }
  //  }
  /**
    * 另一种方式
    */
  //  implicit val girl2Ordering = new Ordering[Girl] {
  //    override def compare(x: Girl, y: Girl): Int = {
  //      x.faceValue - y.faceValue
  //    }
  //  }
  /**
    * Object的写法
    */
  trait girl2Ordering extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      x.faceValue - y.faceValue
    }
  }

  implicit object Girl extends girl2Ordering

}

// //视图界定 必须传入一个隐式转换的函数
//class Chooser[T <% Ordered[T]] {
//  def choose(first: T, second: T): T = {
//    if (first > second) first else second
//  }
//}
//上下文界定 要求必须传入一个隐式转换的值
class Chooser[T: Ordering] {
  def choose(first: T, second: T): T = {
    val ord = implicitly[Ordering[T]]
    if (ord.gt(first, second)) first else second
  }

}

object Chooser {
  def main(args: Array[String]): Unit = {
    import Context._
    val c = new Chooser[Girl]
    val g1 = new Girl("anaglebaby", 90)
    val g2 = new Girl("hat", 99)

    val g = c.choose(g1, g2)
    print(g.name)
  }
}
