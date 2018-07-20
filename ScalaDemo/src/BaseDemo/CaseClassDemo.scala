package BaseDemo

import scala.util.Random

case class SubmitTask(id: String, name: String)

/**
  * case class 为多利对象，可以创建多个
  *
  * @param time
  */
case class HeartBeat(time: Long)

//case object 为单例对象，只有一个
case object CheckTimeOutTast {
  println("this check has bean invoke")
}


object CaseClassDemo extends App {
  var arr = Array(CheckTimeOutTast, HeartBeat(12333), SubmitTask("11", "ggq"))
  val a = CheckTimeOutTast
  val b = CheckTimeOutTast
  println(s"a: $a b: $b")
  arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) => {
      print(s"id : $id name : $name")
    }
    case HeartBeat(time) => {
      print(time)
    }
    case CheckTimeOutTast => {
      print("check")
    }
  }
}
