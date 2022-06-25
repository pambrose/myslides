package lambdas

fun main() {
  //sampleStart

  val doubleToString: (Int) -> String = { it.toString() + it.toString() }

  val doubleToInt: (Int) -> Int = { it + it }

  println(doubleToString(10))
  println(doubleToInt(11))
  //sampleEnd
}