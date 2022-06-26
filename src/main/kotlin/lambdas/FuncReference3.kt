package lambdas

fun main() {
  //sampleStart

  fun add(a: Int, b: Int): Int {
    return a + b
  }

  val x: (Int, Int) -> Int = ::add

  fun greeting(): String {
    return "Hello"
  }

  val y: () -> String = ::greeting
 //sampleEnd
}