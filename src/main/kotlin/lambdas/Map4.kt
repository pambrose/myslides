package lambdas

fun main() {
  //sampleStart

  val intList =
    listOf(3, 4, 5, 6)
      .map { it * it }
      .onEach { println("Mult $it") }
      .map { it.toString() }
      .map { it + it }
      .onEach { println("Add $it") }
      .map { it.length }

  println(intList)
  //sampleEnd
}
