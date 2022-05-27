package lambdas

fun main() {
  //sampleStart
  fun sum(a: Int, b: Int): Int {    // explicit Int return type
    return a + b
  }

  println(sum(5, 7))
  //sampleEnd
}
