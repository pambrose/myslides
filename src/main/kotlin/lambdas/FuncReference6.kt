package lambdas

fun main() {
  fun func1(): Unit {
    println("Hello")
  }

  fun func2(): Int {
    return 5
  }

  fun func3(a: String): Int {
    return a.toInt()
  }

  fun func4(a: Int, b: Int): String {
    return "$a and $b"
  }

  fun func5(a: Int, b: Int): String {
    return "The values are: $a and $b"
  }

  fun func6(a: Int, b: Int): Int {
    return a * b
  }

  //sampleStart

  val a: () -> Unit = ::func1
  val b: () -> Int = ::func2
  val c: (String) -> Int = ::func3
  val d: (Int, Int) -> String = ::func4
  val e: (Int, Int) -> String = ::func5
  val f: (Int, Int) -> Int = ::func6
  //sampleEnd
}