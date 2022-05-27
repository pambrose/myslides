package lambdas

fun main() {

  //sampleStart
  fun func1(): Unit = println("Hello")
  val a: () -> Unit = ::func1

  fun func2(): String = "Hello"
  val b: () -> String = ::func2

  fun func3(a: Int) = println(a * a)
  val c: (Int) -> Unit = ::func3

  fun func4(a: Int, b: Int) = a * b
  val d: (Int, Int) -> Int = ::func4
  //sampleEnd
}