package lambdas

fun main() {
  //sampleStart

  val intList =
    listOf(0, 1, 2, 3, 4, 5, 6)
      .filterIndexed { index, v -> index % 2 == 0  || v == 5}

  println(intList)
  //sampleEnd
}
