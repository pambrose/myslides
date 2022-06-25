package lambdas

fun main() {
  //sampleStart

  val sumLambda1: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

  val sumLambda2: (Int, Int) -> Int = { a, b -> a + b }

  val sumLambda3 = { a: Int, b: Int -> a + b }
  //sampleEnd
}