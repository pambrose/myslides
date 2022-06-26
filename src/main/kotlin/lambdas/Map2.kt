package lambdas

fun main() {
  //sampleStart

  val intList =
    listOf(0, 1, 2, 3, 4, 5, 6)
      .map { it * 2 }
      .map { it * it }

  println(intList)
  //sampleEnd
}
