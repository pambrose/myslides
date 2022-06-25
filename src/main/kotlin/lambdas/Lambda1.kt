package lambdas

fun main() {
  //sampleStart

  fun sumFunc(a: Int, b: Int): Int {
    return a + b
  }

  val sumLambda: (Int, Int) -> Int = { a: Int, b: Int ->
    a + b     // last expression is returned
  }

  println(sumFunc(1, 2))
  println(sumLambda.invoke(3, 4))
  println(sumLambda(5, 6))
  //sampleEnd
}
