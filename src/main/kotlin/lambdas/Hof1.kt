package lambdas

fun main() {
  //sampleStart

  fun argHof(
    a: Int,
    b: Int,
    block: (Int, Int) -> Int,
  ): Int {
    return block.invoke(a, b)
  }
  //sampleEnd
}
