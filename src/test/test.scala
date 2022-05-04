import scala.io.Source

package object test

object test extends App{
  val url = "bla"
  val source = Source.fromURL(url)
  val contents = source.mkString
  val json = parse(contents)
  println("json") d
}
