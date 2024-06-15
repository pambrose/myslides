package lambdas

fun main() {
  //sampleStart

  fun sum(
    a: Int,
    b: Int,
  ): Int {
    return a + b
  }

  val a = ::sum           // cheat and infer type

  println(a.invoke(5, 6))
  println(a(9, 8))
  //sampleEnd
}
