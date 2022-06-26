package lambdas

fun main() {
  //sampleStart

  val squareGt100: (Int) -> Boolean = { it * it > 100 }

  val multGt: (Int, Int, Int) -> Boolean = { i1, i2, i3 -> i1 * i2 > i3 }

  println(squareGt100(9))
  println(multGt(9, 10, 75))
  //sampleEnd
}