package lambdas

fun main() {
  //sampleStart
  fun argHof(
    a: Int,
    b: Int,
    ops: List<(Int, Int) -> Int>,
  ): List<Int> {
    val result = mutableListOf<Int>()
    for (op in ops) {
      result.add(op.invoke(a, b))
    }
    return result
  }

  fun addIt(
    a: Int,
    b: Int,
  ): Int {
    return a + b
  }

  val multIt: (Int, Int) -> Int = { a, b -> a * b }

  val ops: List<(Int, Int) -> Int> = listOf(::addIt, multIt, { a, b -> a % b })
  println(argHof(5, 4, ops))
  //sampleEnd
}
