package lambdas

fun main() {
  //sampleStart
  fun argHof(a: Int, b: Int, block: (Int, Int) -> Int): Int {
    return block.invoke(a, b)
  }

  fun addIt(a: Int, b: Int): Int {
    return a + b
  }

  val multIt: (Int, Int) -> Int = { a, b -> a * b }

  val ops: List<(Int, Int) -> Int> = listOf(::addIt, multIt, { a, b -> a % b })
  for (op in ops) {
    println(argHof(5, 4, op))
  }
  //sampleEnd
}
