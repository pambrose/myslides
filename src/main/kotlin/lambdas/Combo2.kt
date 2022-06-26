package lambdas

fun main() {
  //sampleStart

  listOf(0, 1, 2, 3, 4, 5, 6)
    .filter { it % 2 == 0 }
    .map { it * it }
    .filter { it >= 16 }
    .map { "The values are: $it" }
    .also { println(it) }
  //sampleEnd
}
