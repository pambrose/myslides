package lambdas

fun main() {
  //sampleStart
  fun comboHof(op: String): (Int, Int) -> Int {
    return when (op) {
      "*" -> { a, b -> a * b }
      "+" -> { a, b -> a + b }
      else -> { a, b -> a - b }
    }
  }

  val funcRef: (Int, Int) -> Int = comboHof("+")

  println(funcRef(1, 2))

  println(comboHof("*")(7, 3))
  println(comboHof("+")(5, 3))
  //sampleEnd
}
