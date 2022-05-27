package lambdas

fun main() {

  //sampleStart
  fun sum(a: Int, b: Int) = a + b

  val a = ::sum

  println(a)
  println(a.invoke(7, 7))
  //sampleEnd
}
