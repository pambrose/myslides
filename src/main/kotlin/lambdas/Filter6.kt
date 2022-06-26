package lambdas

fun main() {
  //sampleStart

  val strList =
    listOf(0, "dog", "cat", 3, 44.5, 5, "house", null)
      .filterNotNull()
      .filterIsInstance<String>()
      .filter { it.length > 3 }

  println(strList)
  //sampleEnd
}
