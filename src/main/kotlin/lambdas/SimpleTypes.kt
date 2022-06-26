package lambdas

fun main() {
  //sampleStart

  val a: Int = 1            // explicit Int type
  val b: Double = 44.54     // explicit Double type
  val c = "ABC"             // inferred String type
  val d = true              // inferred Boolean type

  println("a = $a, b = $b, c = $c, d = $d")
  //sampleEnd
}
