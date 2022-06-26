package lambdas

fun main() {
  fun String.splitByWS() = split("\\s".toRegex())

  //sampleStart

  val text = """
    Tell me and I forget.
    Tell me and I remember.
    Tell me and I think.
    Tell me and I know.
    """

  text.splitByWS()
    .map { it.replace(".", "") }
    .filter { it.length > 4 }
    .joinToString(" ")
    .also { println(it) }
  //sampleEnd
}
