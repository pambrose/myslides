package lambdas

fun main() {
  //sampleStart

  val atLeast8CharsLong: (String) -> Boolean = { s -> s.length >= 8 }

  val atLeast10CharsLong: (String) -> Boolean = { it.length >= 10 }

  println(atLeast8CharsLong.invoke("This is"))
  println(atLeast10CharsLong("This is a test"))
  //sampleEnd
}