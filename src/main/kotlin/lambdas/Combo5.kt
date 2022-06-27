package lambdas

fun main() {
  //sampleStart

  val text = """
    A poem about a frog
    that was a little green
    and a little blue
    and a little yellow.
    """

  text.lines()
    .map { it.trim() }
    .filter { it.isNotEmpty() }
    .also { println(it) }
    .mapIndexed { i, s -> if (i % 2 == 0) s.uppercase() else s }
    .filterIndexed { i, s ->  i >= 2 }
    .also { println(it) }
  //sampleEnd
}
