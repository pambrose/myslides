package lambdas

fun main() {
  //sampleStart
  fun func1(): String {
    return "Hello"
  }

  fun func2(a: Int, b: Int): Int {
    return a * b
  }

  val a: () -> String = ::func1

  val b: (Int, Int) -> Int = ::func2
  //sampleEnd
}