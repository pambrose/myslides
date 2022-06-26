package lambdas

fun main() {
  //sampleStart

  fun comboHof(block: (Int, Int) -> Int): (Int, Int) -> Int {
    return { x, y -> block.invoke(x, y) }
  }

  val comboFunc = comboHof { x, y -> x + y }

  println(comboFunc(1, 2))
  println(comboFunc(4, 5))

  println(comboHof({ x, y -> x % y })(7, 3))
  println(comboHof { x, y -> x % y }(5, 3))
  //sampleEnd
}
