package lambdas

fun main() {
  //sampleStart
  class SomeClass(val value: Int)

  val a: SomeClass = SomeClass(4)   // explicit SomeClass type
  val b = SomeClass(5)              // inferred SomeClass type

  println("a = ${a.value}, b = ${b.value}")
  //sampleEnd
}
