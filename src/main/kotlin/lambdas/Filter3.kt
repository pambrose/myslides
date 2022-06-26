package lambdas

fun main() {
  //sampleStart

  val intList = listOf(0, 1, 2, 3, 4, 5, 6)
  val fluent1 = intList.filter { it % 2 == 0 }.filter { it % 3 == 0 }

  println(fluent1)

  val fluent2 =
    listOf(0, 1, 2, 3, 4, 5, 6)
      .filter { it % 2 == 0 }
      .filter { it % 3 == 0 }

  println(fluent2)
  //sampleEnd
}
