package lambdas

fun main() {
  //sampleStart

  val intList =
    listOf(0, 1, 2, 3, 4, 5, 6)
      .filterNot { it % 2 == 0 }
      .filterNot { it % 3 == 0 }

  println(intList)
  //sampleEnd
}
