package lambdas

fun main() {
  //sampleStart

  fun returnHof(): (Int, Int) -> Int {
    return { x, y -> x + y }
  }
  //sampleEnd
}
