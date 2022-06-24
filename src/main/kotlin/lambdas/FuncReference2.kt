package lambdas

fun main() {
  //sampleStart
  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  val a = ::sum

  println(a.invoke(5, 6))
  println(a(7, 7))
  //sampleEnd
}
