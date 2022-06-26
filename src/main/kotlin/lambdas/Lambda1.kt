package lambdas

fun main() {
  //sampleStart
  fun sumFunc(a: Int, b: Int): Int {
    return a + b
  }
  val sumVar = ::sumFunc

  println(sumFunc(1, 2))
  println(sumVar(4, 6))

  val sumLambda: (Int, Int) -> Int = { a: Int, b: Int ->
    a + b     // last expression is returned
  }
  val prodLambda: (Int, Int) -> Int = { a: Int, b: Int -> a * b }

  println(sumLambda.invoke(3, 4))
  println(prodLambda(5, 6))
  //sampleEnd
}
