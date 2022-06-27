package lambdas

fun main() {
  //sampleStart

  fun returnHof(): (Int, Int) -> Int {
    return { x, y -> x + y }
  }

  val hofFunc = returnHof()

  println(hofFunc.invoke(1, 2))
  println(hofFunc(1, 2))
  //sampleEnd
}
