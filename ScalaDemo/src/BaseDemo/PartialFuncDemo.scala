package BaseDemo

object PartialFuncDemo {
  /**
    * 偏函数必须带有PartialFunction的返回类型，且PartialFunction第一个参数为输入类型，第二个参数为输出类型
    * @return
    */
  def func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  /**
    * 是偏函数的另一种形式
    * @param num
    * @return
    */
  def func2(num: String): Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def main(args: Array[String]): Unit = {
    println(func1("one"))
  }
}
