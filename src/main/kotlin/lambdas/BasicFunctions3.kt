package lambdas

fun main() {
  //sampleStart
  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  val a = sum(8, 9)
  val b = sum(1, 3)

  println(a)
  println(b)
  //sampleEnd
}
