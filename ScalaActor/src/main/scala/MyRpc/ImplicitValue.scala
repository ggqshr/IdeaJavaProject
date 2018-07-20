package MyRpc
//所有的隐式值和方法必须放到object中
object context {
  implicit val a = "laozhao"
}

object ImplicitValue {
  def sayHi()(implicit name:String  = "laoduan"): Unit = {
    println(s"hi!! $name")
  }

  def main(args: Array[String]): Unit = {
    import context._
    sayHi()
  }
}
