package lambdas

fun main() {
  //sampleStart

  val intList = listOf(0, 1, 2, 3, 4, 5, 6)
  val chained1 = intList.filter { it % 2 == 0 }.filter { it % 3 == 0 }

  println(chained1)

  val chained2 =
    intList
      .filter { it % 2 == 0 }
      .filter { it % 3 == 0 }

  println(chained2)
  //sampleEnd
}
