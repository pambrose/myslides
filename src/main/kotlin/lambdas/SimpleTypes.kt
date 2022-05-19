package lambdas

// https://hmkcode.com/kotlin/kotlin-function-type-lambda/
fun main() {

  class SomeClass()

  //sampleStart

  val a: Int = 1                          // explicit "Int" type
  val b = "ABC"                           // inferred "String" type
  val c = SomeClass()                     // inferred "SomeClass" type

  fun sum(a: Int, b: Int) = a + b         // inferred return type "Int"
  //sampleEnd
}
