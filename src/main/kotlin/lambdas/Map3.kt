package lambdas

fun main() {
  //sampleStart

  val lenList =
    listOf(0, 1, 2, 3, 4, 5, 6)
      .map { it * it }
      .map { it.toString() }
      .map { it + it }
      .map { it.length }

  println(lenList)
  //sampleEnd
}
