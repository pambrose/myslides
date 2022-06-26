package lambdas

fun main() {
  //sampleStart

  listOf(0, 1, 2, 3, 4, 5, 6)
    .filter { it % 2 == 1 }
    .map { it * it }
    .max()
    .also { println(it) }
  //sampleEnd
}
