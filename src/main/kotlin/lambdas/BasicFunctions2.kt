package lambdas

fun main() {
  //sampleStart
  fun sum(a: Int, b: Int) = a + b         // inferred Int return type

  println(sum(8, 9))
  //sampleEnd
}
