package lambdas

fun main() {
  fun String.splitByWS() = split("\\s".toRegex())

  //sampleStart

  val text = """
    A poem about a frog
    that was a little green
    and a little blue
    and a little yellow.
    """.trimIndent()

  text.lines()
    .mapIndexed { index, s -> if (index % 2 == 0) s.uppercase() else s }
    .filterIndexed { index, s ->  index >= 2 }
    .also { println(it) }
  //sampleEnd
}
