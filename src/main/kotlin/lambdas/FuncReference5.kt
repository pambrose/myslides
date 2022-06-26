package lambdas

fun main() {
  //sampleStart

  fun greeting(name: String): Unit {
    println("Hello, $name")
  }

  val x: (String) -> Unit = ::greeting
 //sampleEnd
}