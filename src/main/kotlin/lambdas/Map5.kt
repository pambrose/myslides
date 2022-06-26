package lambdas

fun main() {
  //sampleStart

  val valList =
    listOf(3, 4, 5, 6)
      .map { it * it }
      .also { println("Mult $it") }
      .map { it.toString() }
      .map { it + it }
      .also { println("Add $it") }
      .map { it.length }

  println(valList)
  //sampleEnd
}
