package lambdas

fun main() {
  //sampleStart
  fun argHof(a: Int, b: Int, block: (Int, Int) -> Int): Int {
    return block.invoke(a, b)
  }

  fun addIt(a: Int, b: Int): Int {
    return a + b
  }
  println(argHof(5, 4, ::addIt))

  val multIt: (Int, Int) -> Int = { a, b -> a * b }
  println(argHof(5, 4, multIt))

  println(argHof(5, 4, { a, b -> a % b }))
  //sampleEnd
}
