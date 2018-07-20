package BaseDemo

class TestClass {
  val id = "111222"
  var name = "tom"
  /**
    * 使用private 修饰的只能在伴生对象中访问，
    */
  private var gender = "男"
  /**
    * 只能在类中使用
    */
  private[this] var pop: String = "混天绫"

  def printPop = {
    println(pop)
  }
}

// 若object的名称和class 相同，则称object为class的伴生对象
//其中定义一些静态方法和变量
object TestClass {
  def main(args: Array[String]): Unit = {
    val p = new TestClass
    p.printPop
  }
}
