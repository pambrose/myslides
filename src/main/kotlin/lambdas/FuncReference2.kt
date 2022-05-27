package lambdas

fun main() {

  //sampleStart
  fun sum(a: Int, b: Int) = a + b

  val a = ::sum        // Let Kotlin infer the type of 'a'

  println(a(5, 6))
  println(a(7, 7))
  //sampleEnd
}
