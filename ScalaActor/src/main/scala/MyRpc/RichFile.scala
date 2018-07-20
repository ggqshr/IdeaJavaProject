package MyRpc

import java.io.File

import scala.io.Source

//门面
object MyPreDef {
  implicit def filetoRichFile(f: File): RichFile = new RichFile(f)
}

class RichFile(val f: File) {
  def read(): String = Source.fromFile(f).mkString
}

object RichFile {
  def main(args: Array[String]): Unit = {
    val f = new File("C:\\Users\\ggq18\\Desktop\\pom.xml")
    //增强的方式
    val ff = new RichFile(f)
    //使用隐式转换的方式
    import MyPreDef._
    val content = f.read()
    //    println(ff.read())
    println(content)
  }
}
