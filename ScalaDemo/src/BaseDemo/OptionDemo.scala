package BaseDemo

object OptionDemo {
  def main(args: Array[String]): Unit = {
    val map = Map(("a", 1), ("b", 2))
    /**
      * map的get方法返回的使用是一个Option对象，
      * Option对象有两个实现类，一个是some,一个是none，如果是some就可以从其中取值
      */
    val v = map.get("b") match {
      case Some(i) => println(i) //如果有内容就返回内容
      case None => 0 //若没有内容就返回一个默认值
    }

    /**
      * 或使用map的getOrElse方法，其本质就是match
      * map.getOrElse()
      */

  }
}
