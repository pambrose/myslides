package lambdas

fun main() {
  //sampleStart
  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  val a = ::sum

  println(a)
  //sampleEnd
}
